package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Transactional
public interface CommonByDateService<DTO, ID extends Serializable> extends CommonService<DTO, ID> {
    List<DTO> findByDate(LocalDate date);
}
