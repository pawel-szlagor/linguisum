package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface CommonService<DTO> {
    DTO save(DTO dto);

    List<DTO> save(Collection<DTO> dtos);
}
