package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Transactional(value = "sourceTransactionManager")
public interface CommonService<DTO, ID extends Serializable> {
    DTO save(DTO dto);

    void save(Collection<DTO> dtos);

    DTO findById(ID id);

    @Transactional(readOnly = true)
    List<DTO> findAll();

    @Transactional(readOnly = true)
    List<DTO> findAllInBulk();

    void saveInBulk(Collection<DTO> collection);
}
