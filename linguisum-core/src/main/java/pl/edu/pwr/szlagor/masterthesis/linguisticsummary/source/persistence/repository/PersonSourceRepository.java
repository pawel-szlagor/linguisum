package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonSource;

/**
 * Created by Pawel on 2017-01-22.
 */
public interface PersonSourceRepository extends JpaRepository<PersonSource, Long> {
}
