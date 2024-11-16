package kz.loader.scheduler;

import kz.loader.service.ItemParserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "app.proxy.enabled")
public class ItemScheduler {

    private final ItemParserService itemParserService;
    @Value("${app.scheduling.enabled.items-desc}")
    private Boolean isParseItemDescEnabled;
    @Value("${app.scheduling.enabled.phones-desc}")
    private Boolean isParsePhonesDescEnabled;
    private final AtomicBoolean isParsingItemsDesc = new AtomicBoolean(false);
    private final AtomicBoolean isParsingPhonesDesc = new AtomicBoolean(false);

    public ItemScheduler(ItemParserService itemParserService) {
        this.itemParserService = itemParserService;
    }

    @Async
    @SneakyThrows
    @Scheduled( cron = "${app.scheduling.cron.parse-items-desc}")
    public void parseItemsDesc() {
        if(!isParseItemDescEnabled || isParsingItemsDesc.get()) {
            return;
        }
        isParsingItemsDesc.set(true);
        System.out.println("parseItemsDesc started! " + OffsetDateTime.now());
        itemParserService.parseItemsDesc(1);
        System.out.println("parseItemsDesc finished! " + OffsetDateTime.now());
        isParsingItemsDesc.set(false);
    }

    @Async
    @SneakyThrows
    @Scheduled(cron = "${app.scheduling.cron.parse-phones-desc}")
    public void parsePhonesDesc() {
        if(!isParsePhonesDescEnabled || isParsingPhonesDesc.get()) {
            return;
        }
        isParsingPhonesDesc.set(true);
        System.out.println("parsePhonesDesc started! " + OffsetDateTime.now());
        itemParserService.parsePhonesDesc(1);
        System.out.println("parsePhonesDesc finished! " + OffsetDateTime.now());
        isParsingPhonesDesc.set(false);
    }

}
