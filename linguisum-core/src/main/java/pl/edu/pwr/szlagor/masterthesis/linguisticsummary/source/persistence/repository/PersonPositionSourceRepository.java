package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionId;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionSource;

/**
 * Created by Pawel on 2017-01-22.
 */
public interface PersonPositionSourceRepository extends JpaRepository<PersonPositionSource, PersonPositionId> {
}
