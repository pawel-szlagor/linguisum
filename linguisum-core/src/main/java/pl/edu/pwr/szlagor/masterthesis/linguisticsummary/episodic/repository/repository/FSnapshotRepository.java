package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository;

import java.util.concurrent.CompletableFuture;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.scheduling.annotation.Async;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FSnapshot;

/**
 * Created by Pawel on 2017-01-16.
 */
@Scope
public interface FSnapshotRepository extends MongoRepository<FSnapshot, ObjectId>, QueryDslPredicateExecutor<FSnapshot> {

    @Async
    CompletableFuture<Page<Snapshot>> findAllBy(Pageable var1);

}
