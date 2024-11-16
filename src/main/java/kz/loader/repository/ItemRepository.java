package kz.loader.repository;

import kz.loader.model.ItemQueue;
import kz.loader.model.KrishaItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<KrishaItem, ObjectId> {
    @Query(value = "{ 'data.phones' : null, $or: [ { 'data.phones' : { $exists: false } }, { 'data.phones' : { $size: 0 } } ] }", sort = "{ 'dtCreate': 1 }")
    List<KrishaItem> getItemsWithoutPhone();
}
