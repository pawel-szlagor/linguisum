package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot.impl;

import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.SnapshotSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.snapshot.SnapshotSourceService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * Created by Paweł on 2017-02-11.
 */
@Service
public class SnapshotSourceServiceImpl implements SnapshotSourceService {
    @Override
    public SnapshotSourceDto findByDateTime(LocalDateTime dateTime, DaySourceDto day) {
        return SnapshotSourceDto.builder().label(day.getLabels().stream().filter(timePeriodPredicate(dateTime, 3L)).findFirst().orElse(null))
                .personPositions(day.getPersonPositions().stream().filter(timePeriodPredicate(dateTime, 3L)).collect(toList()))
                .deviceStates(day.getDeviceStates().stream().filter(timePeriodPredicate(dateTime, 3L)).collect(toList()))
                .mediaUsages(day.getMediaUsages().stream().filter(timePeriodPredicate(dateTime, 3L)).collect(toList()))
                .desiredTemps(day.getDesiredTemps().stream().filter(timePeriodPredicate(dateTime, 3L)).collect(toList()))
                //.weatherConditions(day.getWeatherConditions().stream().filter(timePeriodPredicate(dateTime, 30*60L)).findFirst().get())
                .weatherConditions(day.getWeatherConditions().get(0))
                .build();
    }

    @Override
    public SnapshotSourceDto findByHour(LocalDateTime dateTime, List<DaySourceDto> day) {
        return findByDateTime(dateTime, day.get(dateTime.getHour()));
    }

    private Predicate<ObservationTimeAware> timePeriodPredicate(LocalDateTime dateTime, long secondsDelta) {
        return l -> Duration.between(dateTime, l.getObservationTime()).abs().getSeconds() < secondsDelta || Duration.between(l.getObservationTime(), dateTime).abs().getSeconds() < secondsDelta;
    }


}
