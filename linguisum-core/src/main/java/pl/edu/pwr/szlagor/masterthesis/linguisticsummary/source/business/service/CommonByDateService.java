package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Transactional(value = "sourceTransactionManager")
public interface CommonByDateService<DTO, ID extends Serializable> extends CommonService<DTO, ID> {
    List<DTO> findByDate(LocalDate date);

    List<DTO> findByDateAndHour(LocalDateTime date);

    List<DTO> findByObservationTime(LocalDateTime dateTime);

    Set<DTO> findByObservationTimeAround(LocalDateTime dateTime);

}
