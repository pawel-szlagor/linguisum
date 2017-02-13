package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.WeatherConditionSourceRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent.RAIN;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent.SNOW;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Service
public class WeatherConditionsServiceImpl extends AbstractService<WeatherConditionSourceDto, WeatherConditionSource, Long> implements WeatherConditionsService {

    private final WeatherConditionSourceRepository repository;
    private List<WeatherConditionSourceDto> cachedWeatherConditions;

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

    @Override
    public WeatherConditionSourceDto findByDateTime(LocalDateTime dateTime) {
        if (cachedWeatherConditions == null) {
            this.cachedWeatherConditions = findAllInBulk();
        }
        return cachedWeatherConditions.parallelStream().filter(w -> w.getObservationTime().isBefore(dateTime.plusMinutes(30L)) && w.getObservationTime().isAfter(dateTime.minusMinutes(30L).minusSeconds(1L))).reduce(null, (u, v) -> {
            if (u != null && v != null) throw new IllegalStateException("More than one ID found" + u + v);
            else return u == null ? v : u;
        });
//return getMapperFacade().map(repository.findByObservationTimeBetween(dateTime.minusMinutes(30L), dateTime.plusMinutes(30L).minusSeconds(1L)), getDtoClass());
    }

    @Override
    public List<WeatherConditionSourceDto> findByDate(LocalDate date) {
        if (cachedWeatherConditions == null) {
            this.cachedWeatherConditions = findAllInBulk();
        }
        return cachedWeatherConditions.parallelStream().filter(w -> w.getObservationTime().toLocalDate().equals(date) || w.getObservationTime().toLocalDate().equals(date.plusDays(1))).collect(Collectors.toList());
    }

    @Override
    public double calculateInfluenceWeatherOnDesiredTemp(LocalDateTime dateTime) {
        WeatherConditionSourceDto givenWeather = findByDateTime(dateTime);
        final double tempOut = givenWeather.getTempOut();
        final double windchill = givenWeather.getWindchill();
        final boolean wetEvents = givenWeather.getWeatherEvents().stream().anyMatch(c -> RAIN == c || SNOW == c);
        return -tempOut * 0.2 + 0.5 * windchill + (wetEvents ? 1 : 0.0);
    }

}
