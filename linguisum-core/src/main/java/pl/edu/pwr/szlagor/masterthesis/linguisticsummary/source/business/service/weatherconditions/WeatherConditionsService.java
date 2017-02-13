package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.CommonService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Pawel on 2017-01-29.
 */
public interface WeatherConditionsService extends CommonService<WeatherConditionSourceDto, Long> {

    WeatherConditionSourceDto findByDateTime(LocalDateTime dateTime);
    List<WeatherConditionSourceDto> findByDate(LocalDate date);

    double calculateInfluenceWeatherOnDesiredTemp(LocalDateTime dateTime);

}
