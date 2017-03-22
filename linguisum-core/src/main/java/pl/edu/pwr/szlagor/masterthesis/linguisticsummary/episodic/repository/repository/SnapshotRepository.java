package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.scheduling.annotation.Async;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-01-16.
 */
@Scope
public interface SnapshotRepository extends MongoRepository<Snapshot, BigInteger>, QueryDslPredicateExecutor<Snapshot> {

    List<Snapshot> findByPersonStatesUserId(Long personId);

    Snapshot findByDateAndTime(LocalDate date, LocalTime time);

    List<Snapshot> findByDate(LocalDate date);
    List<Snapshot> findByPersonStatesContaining(PersonState... personStates);

    @Async
    CompletableFuture<Page<Snapshot>> findAllBy(Pageable var1);

}
