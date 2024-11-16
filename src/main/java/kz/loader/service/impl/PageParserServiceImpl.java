package kz.loader.service.impl;

import kz.loader.dto.KrishaPageResponse;
import kz.loader.dto.PageFilterDTO;
import kz.loader.model.KrishaItem;
import kz.loader.model.ItemQueue;
import kz.loader.repository.ItemQueueRepository;
import kz.loader.service.PageService;
import kz.loader.service.PageParserService;
import kz.loader.service.ParsingLogService;
import kz.loader.util.CollectionSplitter;
import kz.loader.util.UtPage;
import kz.loader.util.UtTime;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PageParserServiceImpl implements PageParserService {

    private final PageService pageService;
    private final ItemQueueRepository itemQueueRepository;
    private final ParsingLogService parsingLogService;
    private final MongoTemplate mongoTemplate;
    private List<Long> existingIds;

    public PageParserServiceImpl(PageService pageService, ItemQueueRepository itemQueueRepository, ParsingLogService parsingLogService, MongoTemplate mongoTemplate) {
        this.pageService = pageService;
        this.itemQueueRepository = itemQueueRepository;
        this.parsingLogService = parsingLogService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void init() {
        this.existingIds = mongoTemplate.findDistinct(new Query(), "data.id", KrishaItem.class, Long.class);
    }

    @Override
    @SneakyThrows
    public void parseIdsAsc(List<PageFilterDTO> filters, Integer threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (PageFilterDTO filter : filters) {
            executorService.submit(() -> {
                int currentPageNum = 1;
                int savedItemCount;
                Integer totalPageCount = null;

                try {
                    // завершаем, если наткнулись на страницу, где все id нам знакомы
                    // парсим только первую треть страниц. метод не предназначен, чтобы идти до самого конца
                    Date targetDtCreate = new Date();
                    do {
                        KrishaPageResponse response = pageService.getListData(currentPageNum, filter);
                        savedItemCount = save(response, currentPageNum, UtTime.getAsc(targetDtCreate, currentPageNum * 1000));
                        System.out.println("ASC. URL: " + UtPage.getFullSearchUrl(currentPageNum, filter) + ", Элементов: " + savedItemCount);
                        if(totalPageCount == null) {
                            totalPageCount = response.getDigitalData().getListing().getPagesCount();
                        }
                        currentPageNum++;
                    } while (savedItemCount > 0 && currentPageNum <= 50);

                } catch (Exception exception) {
                    String url = UtPage.getFullSearchUrl(currentPageNum, filter);
                    parsingLogService.save(url, exception.getMessage(), "page.asc", false);
                    System.out.println("ОШИБКА");
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.HOURS);
    }

    @Override
    @SneakyThrows
    public void parseIdsDesc(List<PageFilterDTO> filters, Integer threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<List<PageFilterDTO>> chunks = CollectionSplitter.split(filters, threadCount);
        for(List<PageFilterDTO> chunk : chunks) {
            executorService.submit(() -> {
                for(PageFilterDTO filter : chunk) {
                    Integer totalPageCount = getPagesCount(filter);
                    AtomicInteger currentPageNum = new AtomicInteger(totalPageCount);

                    Date dtCreate = new Date();
                    while (currentPageNum.get() > 0) {
                        int num = currentPageNum.getAndDecrement();
                        try {
                            KrishaPageResponse response = pageService.getListData(num, filter);
                            int savedItemCount = save(response, num, UtTime.getDesc(dtCreate, num * 1000));
                            System.out.println("DESC. URL: " + UtPage.getFullSearchUrl(num, filter) + ", Элементов: " + savedItemCount);
                        } catch (Exception exception) {
                            String url = UtPage.getFullSearchUrl(currentPageNum.get(), filter);
                            parsingLogService.save(url, exception.getMessage(), "page.desc", false);
                            System.out.println("ОШИБКА");
                        }
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.HOURS);
    }

    public Integer getPagesCount(PageFilterDTO filter) {
        KrishaPageResponse response = pageService.getListData(1, filter);
        return response.getDigitalData().getListing().getPagesCount();
    }

    public int save(KrishaPageResponse response, Integer pageNum, Date dtCreate) {
        List<Long> ids = response.getSearch().getIds();
        List<Long> notExistingIds = new ArrayList<>();

        for (Long id : ids) {
            if (!existingIds.contains(id)) {
                notExistingIds.add(id);
            }
        }
        if(!notExistingIds.isEmpty()) {
            existingIds.addAll(notExistingIds);
            ItemQueue itemQueue = new ItemQueue(notExistingIds, dtCreate, pageNum);
            itemQueueRepository.save(itemQueue);
        }
        return notExistingIds.size();
    }
}
