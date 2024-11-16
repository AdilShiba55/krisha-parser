package kz.loader.repository;

import kz.loader.model.ParsingLog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParsingLogRepository extends MongoRepository<ParsingLog, ObjectId> {
}
