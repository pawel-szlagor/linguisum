package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonByDateService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionId;

import javax.transaction.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Transactional
public interface PersonPositionSourceService extends CommonByDateService<PersonPositionSourceDto, PersonPositionId> {

}
