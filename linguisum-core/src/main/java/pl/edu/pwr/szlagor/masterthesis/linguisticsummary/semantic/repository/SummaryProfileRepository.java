package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.SummaryProfile;

/**
 * Created by Pawel on 2017-03-17.
 */
@Repository
public interface SummaryProfileRepository extends MongoRepository<SummaryProfile, ObjectId> {

}
