package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface SnapshotRepository extends MongoRepository<Snapshot, LocalDateTime> {

    List<Snapshot> findByPersonStatesUserId(Long personId);

    Snapshot findByTimestamp(LocalDateTime time);
    List<Snapshot> findByTimestampBetween(LocalDateTime gte, LocalDateTime lt);
    List<Snapshot> findByPersonStatesContaining(PersonState... personStates);

}
