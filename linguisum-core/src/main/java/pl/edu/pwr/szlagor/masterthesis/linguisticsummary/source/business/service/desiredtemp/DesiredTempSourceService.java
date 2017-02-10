package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp;

import java.util.Collection;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface DesiredTempSourceService extends CommonService<DesiredTempSourceDto, Long> {

    void saveInBulk(Collection<DesiredTempSourceDto> desiredTempSourceDtos);
}
