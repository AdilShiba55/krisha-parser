package kz.loader.repository;

import kz.loader.model.ItemQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"app.scheduling.enabled=false"})
class ItemQueueRepositoryTest {

    private final ItemQueueRepository itemQueueRepository;

    @Autowired
    public ItemQueueRepositoryTest(ItemQueueRepository itemQueueRepository) {
        this.itemQueueRepository = itemQueueRepository;
    }

    @Test
    void getItemQueue() {
        List<ItemQueue> queue = itemQueueRepository.getItemQueue();
        Assertions.assertNotNull(queue);
    }
}
