package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.impl;

import static java.util.stream.Collectors.toList;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivityLabelSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.ActivityService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.DesiredTempSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.devicestate.DeviceStateSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.label.ActivityLabelService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.mediausage.MediaUsageSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition.PersonPositionSourceService;

/**
 * Created by Pawel on 2017-02-02.
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    private final PersonPositionSourceService personPositionSourceService;

    private final DesiredTempSourceService desiredTempSourceService;

    private final MediaUsageSourceService mediaUsageSourceService;

    private final DeviceStateSourceService deviceStateSourceService;
    private final ActivityLabelService activityLabelService;

    @Autowired
    public ActivityServiceImpl(PersonPositionSourceService personPositionSourceService, DesiredTempSourceService desiredTempSourceService, MediaUsageSourceService mediaUsageSourceService, DeviceStateSourceService deviceStateSourceService, ActivityLabelService activityLabelService) {
        this.personPositionSourceService = personPositionSourceService;
        this.desiredTempSourceService = desiredTempSourceService;
        this.mediaUsageSourceService = mediaUsageSourceService;
        this.deviceStateSourceService = deviceStateSourceService;
        this.activityLabelService = activityLabelService;
    }

    @Override
    public void save(ActivitySourceDto activitySourceDto) {
        personPositionSourceService.save(activitySourceDto.getPersonPositionSourceDtos());
        desiredTempSourceService.save(activitySourceDto.getDesiredTempSourceDtos());
        mediaUsageSourceService.save(activitySourceDto.getMediaUsageSourceDtos());
        deviceStateSourceService.save(activitySourceDto.getDeviceStateSourceDtos());
    }

    @Override
    public void save(Collection<ActivitySourceDto> activitySourceDtos) {
        System.out.println("Start time: " + LocalDateTime.now());
        List<PersonPositionSourceDto> positionSourceDtos = activitySourceDtos.stream().map(ActivitySourceDto::getPersonPositionSourceDtos).flatMap(Collection::stream).collect(toList());
        personPositionSourceService.saveInBulk(positionSourceDtos);
        System.out.println("persons pos persisted: " + LocalDateTime.now() + " Collection size: " + positionSourceDtos.size());
        List<DesiredTempSourceDto> tempSourceDtos = activitySourceDtos.stream().map(ActivitySourceDto::getDesiredTempSourceDtos).flatMap(Collection::stream).collect(toList());
        desiredTempSourceService.saveInBulk(tempSourceDtos);
        System.out.println("desiredTemp persisted: " + LocalDateTime.now() + " Collection size: " + tempSourceDtos.size());
        List<MediaUsageSourceDto> usageSourceDtos = activitySourceDtos.stream().map(ActivitySourceDto::getMediaUsageSourceDtos).flatMap(Collection::stream).collect(toList());
        mediaUsageSourceService.saveInBulk(usageSourceDtos);
        System.out.println("mediaUsage persisted: " + LocalDateTime.now() + " Collection size: " + usageSourceDtos.size());
        List<DeviceStateSourceDto> stateSourceDtos = activitySourceDtos.stream().map(ActivitySourceDto::getDeviceStateSourceDtos).flatMap(Collection::stream).collect(toList());
        deviceStateSourceService.saveInBulk(stateSourceDtos);
        System.out.println("devicestateSourceDtos persisted: " + LocalDateTime.now() + " Collection size: " + stateSourceDtos.size());
        List<ActivityLabelSourceDto> activityLabelSourceDtos = activitySourceDtos.stream().map(ActivitySourceDto::getActivityLabelSourceDtos).flatMap(Collection::stream).collect(toList());
        activityLabelService.saveInBulk(activityLabelSourceDtos);
        System.out.println("activityLabelSourceDtos persisted: " + LocalDateTime.now() + " Collection size: " + stateSourceDtos.size());
    }
}
