package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import java.time.LocalDateTime;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;

/**
 * Created by Pawel on 2017-01-22.
 */
public interface WeatherConditionSourceRepository extends ObservationTimeAwareRepository<WeatherConditionSource, Long> {

    WeatherConditionSource findByObservationTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
