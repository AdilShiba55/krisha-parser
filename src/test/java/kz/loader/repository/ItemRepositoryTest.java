package kz.loader.repository;

import kz.loader.model.KrishaItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    void getAll() {
        List<KrishaItem> items = itemRepository.findAll();
        Assertions.assertNotNull(items);
    }

    @Test
    void getItemsWithoutPhone() {
        List<KrishaItem> items = itemRepository.getItemsWithoutPhone();
        Assertions.assertNotNull(items);
    }
}
