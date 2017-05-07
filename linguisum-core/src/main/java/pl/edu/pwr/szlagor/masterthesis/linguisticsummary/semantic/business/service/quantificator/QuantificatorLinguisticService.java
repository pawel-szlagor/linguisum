package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.quantificator;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.QuantificatorLinguisticDto;

/**
 * Created by Pawel on 2017-05-05.
 */
public interface QuantificatorLinguisticService {
    @Transactional(value = "semanticTransactionManager")
    QuantificatorLinguisticDto save(QuantificatorLinguisticDto QuantificatorLinguisticDto);

    @Transactional(value = "semanticTransactionManager")
    void save(Collection<QuantificatorLinguisticDto> QuantificatorLinguisticDtos);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    QuantificatorLinguisticDto findById(Long aLong);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    String findLabelByValue(Double value);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    List<QuantificatorLinguisticDto> findAll();
}
