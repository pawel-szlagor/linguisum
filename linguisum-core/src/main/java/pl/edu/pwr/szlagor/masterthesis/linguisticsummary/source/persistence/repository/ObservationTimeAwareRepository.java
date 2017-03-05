package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Pawel on 2017-02-21.
 */
@NoRepositoryBean
public interface ObservationTimeAwareRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findByObservationTime(LocalDateTime observationTime);
}
