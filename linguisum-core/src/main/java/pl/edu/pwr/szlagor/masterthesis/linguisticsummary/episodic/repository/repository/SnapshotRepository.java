package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface SnapshotRepository extends MongoRepository<Snapshot, ObjectId> {

    List<Snapshot> findByPersonStatesPersonId(Long personId);
}
