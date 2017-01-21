package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.Room;

/**
 * Created by Pawel on 2017-01-16.
 */
public interface RoomRepository extends MongoRepository<Room, Long> {

}
