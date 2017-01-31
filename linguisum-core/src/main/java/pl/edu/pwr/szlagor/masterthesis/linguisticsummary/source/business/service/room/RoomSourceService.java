package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface RoomSourceService extends CommonService<RoomSourceDto, Long> {

    RoomSourceDto findByName(String name);
}
