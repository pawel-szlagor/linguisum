package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface MemGradeService extends CommonService<TrapezoidalMemGradeDto, Long> {
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    List<TrapezoidalMemGradeDto> findByProperty(String property);

    double calculateDistance(Double one, Double other, String property);


}
