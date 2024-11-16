package kz.loader.repository;

import kz.loader.model.ItemQueue;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemQueueRepository extends MongoRepository<ItemQueue, ObjectId> {
    @Query(value = "{}", sort = "{dtCreate:  1}")
    List<ItemQueue> getItemQueue();
}
