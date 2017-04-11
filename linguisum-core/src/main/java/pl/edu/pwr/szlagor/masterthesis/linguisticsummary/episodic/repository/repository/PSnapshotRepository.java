package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import java.util.concurrent.CompletableFuture;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.scheduling.annotation.Async;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface PSnapshotRepository extends MongoRepository<PSnapshot, ObjectId>, QueryDslPredicateExecutor<PSnapshot> {

    @Async
    CompletableFuture<Page<Snapshot>> findAllBy(Pageable var1);

}
