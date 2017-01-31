package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions
        .WeatherConditionsService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.WeatherConditionSourceRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Service
public class WeatherConditionsServiceImpl extends AbstractService<WeatherConditionSourceDto, WeatherConditionSource,
        Long>
        implements WeatherConditionsService {

    private final WeatherConditionSourceRepository repository;

    @Autowired
    public WeatherConditionsServiceImpl(WeatherConditionSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<WeatherConditionSource> getEntityClass() {
        return WeatherConditionSource.class;
    }

    @Override
    protected Class<WeatherConditionSourceDto> getDtoClass() {
        return WeatherConditionSourceDto.class;
    }
}
