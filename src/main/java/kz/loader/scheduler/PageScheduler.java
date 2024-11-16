package kz.loader.scheduler;

import kz.loader.dto.PageFilterDTO;
import kz.loader.service.PageParserService;
import kz.loader.util.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "app.proxy.enabled")
public class PageScheduler {

    private final PageParserService pageParserService;
    private final List<PageFilterDTO> filters;
    @Value("${app.scheduling.enabled.ids-asc}")
    private Boolean isParseIdsAscEnabled;
    @Value("${app.scheduling.enabled.ids-desc}")
    private Boolean isParseIdsDescEnabled;
    private final AtomicBoolean isParsingIdsAsc = new AtomicBoolean(false);
    private final AtomicBoolean isParsingIdsDesc = new AtomicBoolean(false);

    public PageScheduler(PageParserService pageParserService) {
        this.pageParserService = pageParserService;
        this.filters = new ArrayList<>();
        for (DistrictEnum districtEnum : getDistricts()) {
            this.filters.addAll(getFilters(districtEnum));
        }
    }

    @Async
    @SneakyThrows
    @Scheduled(cron = "${app.scheduling.cron.parse-ids-asc}")
    public void parseIdsAsc() {
        if (!isParseIdsAscEnabled || isParsingIdsDesc.get() || isParsingIdsAsc.get()) {
            return;
        }
        isParsingIdsAsc.set(true);
        pageParserService.parseIdsAsc(filters, 1);
        System.out.println("parseIdsAsc finished! " + OffsetDateTime.now());
        isParsingIdsAsc.set(false);
    }

    @Async
    @SneakyThrows
    @Scheduled(cron = "${app.scheduling.cron.parse-ids-desc}")
    public void parseIdsDesc() {
        if (!isParseIdsDescEnabled || isParsingIdsDesc.get()) {
            return;
        }
        isParsingIdsDesc.set(true);
        System.out.println("parseIdsDesc started! " + OffsetDateTime.now());
        pageParserService.parseIdsDesc(filters, 3);
        System.out.println("parseIdsDesc finished! " + OffsetDateTime.now());
        isParsingIdsDesc.set(false);
    }

    private List<DistrictEnum> getDistricts() {
        return Arrays.asList(
//            DistrictEnum.ALMATY_ALATAUSKY,
//            DistrictEnum.ALMATY_ALMALINSKY,
//            DistrictEnum.ALMATY_AUEZOVSKY,
//            DistrictEnum.ALMATY_BOSTANDIKSKY,
//            DistrictEnum.ALMATY_ZHETISUSKY,
//            DistrictEnum.ALMATY_MEDEUSKY,
//            DistrictEnum.ALMATY_NAURIZBAYSKY,
//            DistrictEnum.ALMATY_TURKSIBSKY,
//
//            DistrictEnum.ASTANA_ALMATYNSKY,
//            DistrictEnum.ASTANA_ESILSKY,
//            DistrictEnum.ASTANA_NURA,
//            DistrictEnum.ASTANA_SARIARKYNSKY,
//            DistrictEnum.ASTANA_BAIKONUR,

            DistrictEnum.ASTANA
//            DistrictEnum.ALMATY,
//            DistrictEnum.SHYMKENT,
//            DistrictEnum.ABAY_OBLAST,
//            DistrictEnum.AKMOLYNSKAYA_OBLAST,
//            DistrictEnum.AKTYUBINSKAYA_OBLAST,
//            DistrictEnum.ALMATINSKAYA_OBLAST,
//            DistrictEnum.ATIRAUSKAYA_OBLAST,
//            DistrictEnum.VOSTOCHNO_KAZAKHSTANSKAYA_OBLAST,
//            DistrictEnum.ZHAMBLSKAYA_OBLAST,
//            DistrictEnum.ZHETISAUSKAYA_OBLAST,
//            DistrictEnum.ZAPADNO_KAZAKHSTANSKAYA_OBLAST,
//            DistrictEnum.KARAGANDINSKAYA_OBLAST,
//            DistrictEnum.KOSTANAYSKAYA_OBLAST,
//            DistrictEnum.KYZYLORDINSKAYA_OBLAST,
//            DistrictEnum.MANGISTAUSKAYA_OBLAST,
//            DistrictEnum.PAVLODARSKAYA_OBLAST,
//            DistrictEnum.SEVERO_KAZAKHSTANSKAYA_OBLAST,
//            DistrictEnum.YUZHNO_KAZAKHSTANSKAYA_OBLAST,
//            DistrictEnum.ULITAUSKAYA_OBLAST
        );
    }

    private List<PageFilterDTO> getFilters(DistrictEnum district) {
        return Arrays.asList(
                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.KVARTIRA, district)
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.DOMADACHI, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.KVARTIRA, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.DOM, district),
//
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.GARAZH, district),
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.KOMMERCHESKAYA_NEDVIZHIMOST, district),
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.PROMBAZA, district),
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.PROCHAYA_NEDVIZHIMOST, district),
//                new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.ZARUBEZHNAYA_NEDVIZHIMOST, district),
//
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.KOMNATA, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.DACHA, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.GARAZH, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.KOMMERCHESKAYA_NEDVIZHIMOST, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.PROMBAZA, district),
//                new PageFilterDTO(ActionEnum.ARENDA, ObjectTypeEnum.PROCHAYA_NEDVIZHIMOST, district)
        );
    }

}
