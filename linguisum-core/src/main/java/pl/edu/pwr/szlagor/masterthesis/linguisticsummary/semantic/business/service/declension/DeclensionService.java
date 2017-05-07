package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.declension;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.DeclensionDto;

/**
 * Created by Pawel on 2017-05-05.
 */
public interface DeclensionService {
    @Transactional(value = "semanticTransactionManager")
    DeclensionDto save(DeclensionDto DeclensionDto);

    @Transactional(value = "semanticTransactionManager")
    void save(Collection<DeclensionDto> DeclensionDtos);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    DeclensionDto findById(Long aLong);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    DeclensionDto findByNominative(String nominative);

    @Transactional(readOnly = true, value = "semanticTransactionManager")
    List<DeclensionDto> findAll();
}
