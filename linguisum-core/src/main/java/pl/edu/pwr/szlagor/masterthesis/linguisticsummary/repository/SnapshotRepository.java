package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.Snapshot;

import java.util.List;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface SnapshotRepository extends MongoRepository<Snapshot, ObjectId> {

    List<Snapshot> findByPersonStatesPersonId(Long personId);
}
