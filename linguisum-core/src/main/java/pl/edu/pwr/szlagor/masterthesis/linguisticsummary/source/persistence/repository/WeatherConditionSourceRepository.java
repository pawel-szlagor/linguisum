package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;

/**
 * Created by Pawel on 2017-01-22.
 */
public interface WeatherConditionSourceRepository extends JpaRepository<WeatherConditionSource, Long> {

    WeatherConditionSource findByObservationTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
