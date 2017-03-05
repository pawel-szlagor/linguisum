package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot.SnapshotSourceService;

/**
 * Created by Paweł on 2017-02-11.
 */
@Service
public class SnapshotSourceServiceImpl implements SnapshotSourceService {
    @Override
    public synchronized SnapshotSourceDto findByDateTime(LocalDateTime dateTime, DaySourceDto day) {
        return SnapshotSourceDto.builder().label(day.getLabels().get(dateTime) != null ? day.getLabels().get(dateTime).get(0) : null)
                .personPositions(day.getPersonPositions().getOrDefault(dateTime, Collections.emptyList()))
                .deviceStates(day.getDeviceStates().getOrDefault(dateTime, Collections.emptyList()))
                .mediaUsages(day.getMediaUsages().getOrDefault(dateTime, Collections.emptyList()))
                .desiredTemps(day.getDesiredTemps().getOrDefault(dateTime, Collections.emptyList()))
                .weatherConditions(day.getWeatherConditions().stream().filter(timePeriodPredicate(dateTime, 30 * 60)).findFirst().get())
                .build();
    }

    private Predicate<ObservationTimeAware> timePeriodPredicate(LocalDateTime dateTime, double secondsDelta) {
        return l -> Duration.between(dateTime, l.getObservationTime()).abs().getSeconds() <= secondsDelta;
    }

}
