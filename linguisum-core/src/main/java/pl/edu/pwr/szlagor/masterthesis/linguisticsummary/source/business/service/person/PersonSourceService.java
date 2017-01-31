package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface PersonSourceService extends CommonService<PersonSourceDto, Long> {

    PersonSourceDto findByName(String name);
}
