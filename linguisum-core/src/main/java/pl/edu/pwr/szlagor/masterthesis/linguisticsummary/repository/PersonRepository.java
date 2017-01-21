package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.Person;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface PersonRepository extends MongoRepository<Person, Long> {

}
