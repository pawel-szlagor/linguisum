package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.day.impl;

import java.time.LocalDate;

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
        return null;
    }
}
