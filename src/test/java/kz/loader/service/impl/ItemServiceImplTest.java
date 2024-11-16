package kz.loader.service.impl;

import kz.loader.dto.KrishaItemResponse;
import kz.loader.model.KrishaItem;
import kz.loader.repository.ItemRepository;
import kz.loader.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"app.scheduling.enabled=false"})
class ItemServiceImplTest {

    private final ItemService itemService;

    @Autowired
    public ItemServiceImplTest(ItemService itemService) {
        this.itemService = itemService;
    }

    @Test
    void getItemDataWithPhones() {
        KrishaItemResponse response = itemService.getItemDataWithPhones(679228637L);
        Assertions.assertNotNull(response);
        assertFalse(response.getPhones().isEmpty());
    }

    @Test
    void getItemData() {
        KrishaItemResponse response = itemService.getItemData(679228637L, null);
        Assertions.assertNotNull(response);
    }
}
