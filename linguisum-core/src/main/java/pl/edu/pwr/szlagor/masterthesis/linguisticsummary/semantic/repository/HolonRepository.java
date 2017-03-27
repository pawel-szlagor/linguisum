package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-17.
 */
public interface HolonRepository extends MongoRepository<Holon, BigInteger>, QueryDslPredicateExecutor<Holon> {

    List<Holon> findByRelevanceBetween(Double gte, Double le);
}
