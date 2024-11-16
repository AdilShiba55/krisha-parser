package kz.loader.service.impl;

import kz.loader.dto.PageFilterDTO;
import kz.loader.service.PageParserService;
import kz.loader.util.ActionEnum;
import kz.loader.util.DistrictEnum;
import kz.loader.util.ObjectTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageParserServiceImplTest {

    private final PageParserService pageParserService;

    @Autowired
    public PageParserServiceImplTest(PageParserService pageParserService) {
        this.pageParserService = pageParserService;
    }

    @Test
    void getPagesCount() {
        Integer count = pageParserService.getPagesCount(new PageFilterDTO(ActionEnum.PRODAZHA, ObjectTypeEnum.KVARTIRA, DistrictEnum.ASTANA));
        Assertions.assertNotNull(count);
    }
}
