package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day.DaySourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.DesiredTempSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.devicestate.DeviceStateSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.label.ActivityLabelService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.mediausage.MediaUsageSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition.PersonPositionSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Pawel on 2017-02-08.
 */
@Service
public class DaySourceServiceImpl implements DaySourceService {
    private final ActivityLabelService activityLabelService;
    private final DesiredTempSourceService desiredTempSourceService;
    private final DeviceStateSourceService deviceStateSourceService;
    private final MediaUsageSourceService mediaUsageSourceService;
    private final PersonPositionSourceService personPositionSourceService;
    private final WeatherConditionsService weatherConditionsService;

    @Autowired
    public DaySourceServiceImpl(ActivityLabelService activityLabelService, DesiredTempSourceService desiredTempSourceService, DeviceStateSourceService deviceStateSourceService, MediaUsageSourceService mediaUsageSourceService, PersonPositionSourceService personPositionSourceService, WeatherConditionsService weatherConditionsService) {
        this.activityLabelService = activityLabelService;
        this.desiredTempSourceService = desiredTempSourceService;
        this.deviceStateSourceService = deviceStateSourceService;
        this.mediaUsageSourceService = mediaUsageSourceService;
        this.personPositionSourceService = personPositionSourceService;
        this.weatherConditionsService = weatherConditionsService;
    }

    @Override
    public DaySourceDto findDaySourceByDate(LocalDate date) {
        return DaySourceDto.builder().labels(activityLabelService.findByDate(date)).desiredTemps(desiredTempSourceService.findByDate(date)).deviceStates(deviceStateSourceService.findByDate(date)).mediaUsages(mediaUsageSourceService.findByDate(date)).personPositions(personPositionSourceService.findByDate(date)).weatherConditions(weatherConditionsService.findByDate(date)).build();
    }
    
    @Override
    public List<DaySourceDto> findDaySourceByDateHourly(LocalDate date) {
        return IntStream.range(0, 24).mapToObj(i -> LocalDateTime.of(date, LocalTime.of(i, 0))).parallel().map(h -> DaySourceDto.builder().labels(activityLabelService.findByDateAndHour(h)).desiredTemps(desiredTempSourceService.findByDateAndHour(h)).deviceStates(deviceStateSourceService.findByDateAndHour(h)).mediaUsages(mediaUsageSourceService.findByDateAndHour(h)).personPositions(personPositionSourceService.findByDateAndHour(h)).weatherConditions(Collections.singleton(weatherConditionsService.findByDateTime(h))).build()).collect(Collectors.toList());
    }
}
