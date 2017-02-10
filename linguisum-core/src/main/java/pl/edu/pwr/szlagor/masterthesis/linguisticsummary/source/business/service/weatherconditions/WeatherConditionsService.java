package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions;

import java.time.LocalDateTime;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface WeatherConditionsService extends CommonService<WeatherConditionSourceDto, Long> {

    WeatherConditionSourceDto findByDateTime(LocalDateTime dateTime);

    double calculateInfluenceWeatherOnDesiredTemp(LocalDateTime dateTime);

}
