package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.impl;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent.RAIN;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent.SNOW;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.WeatherConditionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.WeatherConditionSourceRepository;

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
    public synchronized WeatherConditionSourceDto findByDateTime(LocalDateTime dateTime) {
        if (cachedWeatherConditions == null) {
            this.cachedWeatherConditions = findAllInBulk();
        }
        return cachedWeatherConditions.parallelStream().filter(w -> w.getObservationTime().isBefore(dateTime.plusMinutes(30L)) && w.getObservationTime().isAfter(dateTime.minusMinutes(30L).minusSeconds(1L))).reduce(null, (u, v) -> u == null ? v : u
        );
//return getMapperFacade().map(repository.findByObservationTimeBetween(dateTime.minusMinutes(30L), dateTime.plusMinutes(30L).minusSeconds(1L)), getDtoClass());
    }

    @Override
    public synchronized List<WeatherConditionSourceDto> findByDate(LocalDate date) {
        if (cachedWeatherConditions == null) {
            this.cachedWeatherConditions = findAllInBulk();
        }
        return cachedWeatherConditions.parallelStream().filter(w -> w.getObservationTime().toLocalDate().equals(date) || w.getObservationTime().toLocalDate().equals(date.plusDays(1))).collect(Collectors.toList());
    }

    @Override
    public synchronized double calculateInfluenceWeatherOnDesiredTemp(LocalDateTime dateTime) {
        WeatherConditionSourceDto givenWeather = null;
        try {
            givenWeather = findByDateTime(dateTime);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (givenWeather == null) {
            givenWeather = findByDateTime(dateTime.plusSeconds(1));
        }
        final double tempOut = givenWeather.getTempOut();
        final double sunlight = givenWeather.getSunlightEmission();
        final boolean wetEvents = givenWeather.getWeatherEvents().stream().anyMatch(c -> RAIN == c || SNOW == c);
        return calculateInfluenceTempOut(tempOut) - sunlight / 500 + (wetEvents ? 1 : 0.0);
    }

    private double calculateInfluenceTempOut(double tempOut) {
        if (tempOut < -20.0) {
            return 5;
        } else if (tempOut < -10) {
            return 4.5;
        } else if (tempOut < -5) {
            return 4;
        } else if (tempOut < 0) {
            return 3.5;
        } else if (tempOut < 5) {
            return 3;
        } else if (tempOut < 10) {
            return 2;
        } else if (tempOut < 15) {
            return 1;
        } else if (tempOut < 20) {
            return 0;
        } else if (tempOut < 22) {
            return -1;
        } else if (tempOut < 25) {
            return -2;
        } else if (tempOut < 28) {
            return -3;
        } else if (tempOut < 30) {
            return -4;
        } else {
            return -5;
        }

    }


}
