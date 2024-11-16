package kz.loader.util;

import kz.loader.model.ItemQueue;
import kz.loader.repository.ItemQueueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"app.scheduling.enabled=false"})
class CollectionSplitterTest {

    private final CollectionSplitter collectionSplitter = new CollectionSplitter();

    @Test
    void split() {
        List<String> queue = Arrays.asList("Adil1", "Adil2", "Adil3", "Adil4", "Adil5", "Adil6");
        List<List<String>> chunks = collectionSplitter.split(queue, 2);
        Assertions.assertNotNull(chunks);
    }
}
