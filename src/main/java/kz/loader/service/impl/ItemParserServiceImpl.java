package kz.loader.service.impl;

import kz.loader.dto.KrishaItemResponse;
import kz.loader.dto.KrishaItemResponseDTO;
import kz.loader.model.ItemQueue;
import kz.loader.model.KrishaItem;
import kz.loader.repository.ItemQueueRepository;
import kz.loader.repository.ItemRepository;
import kz.loader.service.ItemParserService;
import kz.loader.service.ItemService;
import kz.loader.service.ParsingLogService;
import kz.loader.util.CollectionSplitter;
import kz.loader.util.UtItem;
import kz.loader.util.UtPage;
import kz.loader.util.UtTime;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ItemParserServiceImpl implements ItemParserService {

    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final ItemQueueRepository itemQueueRepository;
    private final ParsingLogService parsingLogService;

    public ItemParserServiceImpl(ItemService itemService, ItemRepository itemRepository, ItemQueueRepository itemQueueRepository, ParsingLogService parsingLogService) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
        this.itemQueueRepository = itemQueueRepository;
        this.parsingLogService = parsingLogService;
    }

    @Override
    @SneakyThrows
    public void parseItemsDesc(Integer threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<ItemQueue> queue = itemQueueRepository.getItemQueue();
        List<List<ItemQueue>> chunks = CollectionSplitter.split(queue, threadCount);
        for(List<ItemQueue> chunk : chunks) {
            executorService.submit(() -> {
                for(ItemQueue itemQueue : chunk) {
                    Date queueDtCreate = itemQueue.getDtCreate();
                    List<Long> ids = itemQueue.getIds();
                    for(int i = ids.size(); i > 0; i--) {
                        Long id = ids.get(i-1);
                        try {
                            Date itemDtCreate = UtTime.getDesc(queueDtCreate, i * 2);
                            KrishaItemResponse response = itemService.getItemData(id, null);
                            KrishaItemResponseDTO dto = UtItem.getKrishaItemResponseDTO(response);
                            save(dto, itemDtCreate);
                            ids.remove(i-1);
                            System.out.println("ITEMS. ID: " + id);
                        } catch (Exception exception) {
                            String url = UtItem.getUrl(id);
                            parsingLogService.save(url, exception.getMessage(), "item.desc", false);
                            System.out.println("ОШИБКА");
                        }
                    }
                    if(ids.isEmpty()) {
                        itemQueueRepository.delete(itemQueue);
                    } else {
                        itemQueueRepository.save(itemQueue);
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.HOURS);
    }

    public void save(KrishaItemResponseDTO dto, Date dtCreate) {
        Long id = dto.getId();
        KrishaItem item = new KrishaItem(id, dto, dtCreate, dtCreate);
        itemRepository.save(item);
    }

    @Override
    @SneakyThrows
    public void parsePhonesDesc(Integer threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<KrishaItem> items = itemRepository.getItemsWithoutPhone();
        List<List<KrishaItem>> chunks = CollectionSplitter.split(items, threadCount);
        for(List<KrishaItem> chunk : chunks) {
            executorService.submit(() -> {
                for(KrishaItem item : chunk) {
                    try {
                        KrishaItemResponseDTO data = item.getData();
                        List<String> phones = itemService.getPhones(item.getData().getId(), null).stream()
                                .map(phone -> phone.replaceAll(" ", ""))
                                .collect(Collectors.toList());
                        data.setPhones(phones);
                        itemRepository.save(item);
                        System.out.println("PHONES. ID: " + data.getId());
                    } catch (Exception exception) {
                        String url = UtItem.getPhoneUrl(item.getId());
                        parsingLogService.save(url, exception.getMessage(), "phones.desc", false);
                        System.out.println("ОШИБКА");
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.HOURS);
    }
}
