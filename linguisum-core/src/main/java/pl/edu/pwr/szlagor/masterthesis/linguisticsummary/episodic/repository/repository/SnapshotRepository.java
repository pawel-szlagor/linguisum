package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface SnapshotRepository extends MongoRepository<Snapshot, ObjectId> {

    List<Snapshot> findByPersonStatesUserId(Long personId);

    List<Snapshot> findByTimestampBetween(LocalDateTime gte, LocalDateTime lt);
    List<Snapshot> findByPersonStatesContaining(PersonState... personStates);

}
