package kz.loader.util;

import kz.loader.model.ItemQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionSplitter {
    public static <T> List<List<T>> split(List<T> items, int chunkCount) {
        if(items.size() <= chunkCount) {
            return items.isEmpty() ? Collections.emptyList() : List.of(items);
        }
        List<List<T>> chunks = new ArrayList<>();
        int chunkSize = items.size() / chunkCount;
        int remainder = items.size() % chunkCount;

        int fromIndex = 0;
        for (int i = 0; i < chunkCount; i++) {
            int toIndex = fromIndex + chunkSize + (i < remainder ? 1 : 0);
            chunks.add(items.subList(fromIndex, toIndex));
            fromIndex = toIndex;
        }
        return chunks;
    }
}
