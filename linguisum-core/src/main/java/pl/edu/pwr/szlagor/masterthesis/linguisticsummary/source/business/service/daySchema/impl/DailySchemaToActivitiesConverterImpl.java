package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections.map.IdentityMap;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivityLabelSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DesiredTempSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceStateSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.SampleActivities;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.DailySchemaToActivitiesConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.weatherconditions.WeatherConditionsService;

/**
 * Created by Pawel on 2017-02-02.
 */
@Component
public class DailySchemaToActivitiesConverterImpl implements DailySchemaToActivitiesConverter {

    private static final double COLD_WATER_EFF_BATH_PER_MINUTE = 7;
    private static final double HOT_WATER_EFF_BATH_PER_MINUTE = 7;
    private static final double HOT_WATER_EFF_KITCHEN_PER_MINUTE = 5;
    private static final double COLD_WATER_EFF_KITCHEN_PER_MINUTE = 5;
    private static final double ELECTRICITY_EFF = 0.2;
    private static final double GAS_EFF = 0.1;

    private final WeatherConditionsService weatherConditionsService;

    @Autowired
    public DailySchemaToActivitiesConverterImpl(WeatherConditionsService weatherConditionsService) {
        this.weatherConditionsService = weatherConditionsService;
    }

    @Override
    public synchronized List<ActivitySourceDto> convert(DaySchemaSourceDto daily, LocalDate date) {
        final Map<ActivitySchemaSourceDto, LocalDateTime> startTimes = new IdentityMap(daily.getActivities().size() + 2);
        final ActivitySchemaSourceDto sleepingBefore = SampleActivities.SLEEPING.averageElapsedTime(Duration.between(LocalTime.MIN, daily.getWakingHour()).toMinutes()).build();
        final ActivitySchemaSourceDto sleepingAfter = SampleActivities.SLEEPING.averageElapsedTime(Duration.between(daily.calculateSleepHour(), LocalTime.MAX).toMinutes()).build();
        startTimes.put(sleepingBefore, LocalDateTime.of(date, LocalTime.MIN));
        final List<ActivitySchemaSourceDto> activities = Lists.newArrayList(daily.getActivities());
        activities.add(0, sleepingBefore);
        for (int i = 0; i < activities.size() - 1; i++) {
            LocalDateTime currentTime = startTimes.get(activities.get(i)).plusMinutes(Math.round(activities.get(i).getAverageElapsedTime()));
            startTimes.put(activities.get(i + 1), currentTime);
        }
        startTimes.put(sleepingAfter, startTimes.get(activities.get(activities.size() - 1)).plusMinutes(Math.round(activities.get(activities.size() - 1).getAverageElapsedTime())));
        activities.add(sleepingAfter);
        return activities.stream().map(l -> ActivitySourceDto.builder().name(l.getName()).mediaUsageSourceDtos(convertMediaUsage(startTimes.get(l), l)).deviceStateSourceDtos(convertDeviceState(startTimes.get(l), l)).personPositionSourceDtos(convertPersonPosition(startTimes.get(l), daily.getPersonSourceDto(), l)).desiredTempSourceDtos(convertDesiredTemp(startTimes.get(l), daily.getPersonSourceDto(), l)).activityLabelSourceDtos(convertLabels(startTimes.get(l), l)).build()).collect(Collectors.toList());
    }

    private synchronized List<ActivityLabelSourceDto> convertLabels(LocalDateTime startTime, ActivitySchemaSourceDto activitySchema) {
        final LocalDateTime endTime = startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()));
        final long eventDuration = Duration.between(startTime, endTime).toMinutes() * 60;
        return IntStream.iterate(0, i -> i + 5).limit(eventDuration / 5).mapToObj(i -> ActivityLabelSourceDto.builder().label(activitySchema.getName()).observationTime(startTime.plusSeconds(i)).build()).collect(Collectors.toList());
    }

    private synchronized List<MediaUsageSourceDto> convertMediaUsage(LocalDateTime startTime, ActivitySchemaSourceDto activitySchema) {
        final LocalDateTime endTime = startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()));
        final List<MediaUsageSourceDto> entries = activitySchema.getMediaUsageSourceDtos();
        long eventDuration = Duration.between(startTime, endTime).toMinutes() * 60;
        final List<MediaUsageSourceDto> results = Lists.newArrayListWithExpectedSize(Math.toIntExact(eventDuration / 5));
        for (int i = 0; i < eventDuration; i += 5) {
            final LocalDateTime currentTime = startTime.plusSeconds(i);
            results.addAll(entries.stream().map(l -> MediaUsageSourceDto.builder().usagePerMinute(l.getUsagePerMinute()).location(l.getLocation()).observationTime(currentTime).mediaType(l.getMediaType()).build()).collect(Collectors.toList()));
        }
        return results;
    }

    private synchronized List<DeviceStateSourceDto> convertDeviceState(LocalDateTime startTime, ActivitySchemaSourceDto activitySchema) {
        final Random random = new Random();
        final LocalDateTime endTime = startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()));
        final Set<Map.Entry<DeviceSourceDto, Double>> entries = activitySchema.getTimeOfDeviceUsages().entrySet();
        final Map<DeviceSourceDto, LocalDateTime> startTimes = entries.stream().map(Map.Entry::getKey).collect(Collectors.toMap(o -> o, o -> startTime.plusMinutes(random.nextInt((int) Duration.between(startTime, startTime.plusMinutes(Math.max(1, Math.round(activitySchema.getAverageElapsedTime())))).toMinutes()))));
        final Map<DeviceSourceDto, LocalDateTime> endTimes = entries.stream().collect(Collectors.toMap(Map.Entry::getKey, o -> startTimes.get(o.getKey()).plusMinutes(Math.round(o.getValue() * Duration.between(startTime, startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()))).toMinutes()))));

        final long eventDuration = Duration.between(startTime, endTime).toMinutes() * 60;
        final List<DeviceStateSourceDto> results = Lists.newArrayListWithExpectedSize(Math.toIntExact(eventDuration / 5));
        for (int i = 0; i < eventDuration; i += 5) {
            final LocalDateTime currentTime = startTime.plusSeconds(i);
            results.addAll(entries.stream().filter(l -> currentTime.isAfter(startTimes.get(l.getKey()).minusSeconds(1)) && currentTime.isBefore(endTimes.get(l.getKey()))).map(l -> DeviceStateSourceDto.builder().device(l.getKey()).observationTime(currentTime).isWorking(true).build()).collect(Collectors.toList()));
        }
        return results;
    }

    private synchronized List<PersonPositionSourceDto> convertPersonPosition(LocalDateTime startTime, PersonSourceDto user, ActivitySchemaSourceDto activitySchema) {
        final LocalDateTime endTime = startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()));
        final List<RoomSourceDto> entries = activitySchema.getLocationProbabilities().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        final Map<RoomSourceDto, LocalDateTime> startTimes = new IdentityMap();
        final Map<RoomSourceDto, LocalDateTime> endTimes = new IdentityMap();
        for (int i = 0; i < entries.size(); i++) {
            final LocalDateTime currentTime = i == 0 ? startTime : endTimes.get(entries.get(i - 1));
            startTimes.put(entries.get(i), currentTime);
            endTimes.put(entries.get(i), i == entries.size() - 1 ? endTime : currentTime.plusMinutes(Math.round(activitySchema.getLocationProbabilities().get(entries.get(i)) * activitySchema.getAverageElapsedTime())));
        }
        long eventDuration = Duration.between(startTime, endTime).toMinutes() * 60;
        List<PersonPositionSourceDto> results = Lists.newArrayListWithExpectedSize(Math.toIntExact(eventDuration / 5));
        for (int i = 0; i < eventDuration; i += 5) {
            LocalDateTime currentTime = startTime.plusSeconds(i);
            results.addAll(activitySchema.getLocationProbabilities().entrySet().stream().filter(l -> currentTime.isAfter(startTimes.get(l.getKey()).minusSeconds(1)) && currentTime.isBefore(endTimes.get(l.getKey()))).map(l -> PersonPositionSourceDto.builder().user(user).location(l.getKey()).observationTime(currentTime).build()).collect(Collectors.toList()));
        }
        return results;
    }

    private synchronized List<DesiredTempSourceDto> convertDesiredTemp(LocalDateTime startTime, PersonSourceDto user, ActivitySchemaSourceDto activitySchema) {
        final LocalDateTime endTime = startTime.plusMinutes(Math.round(activitySchema.getAverageElapsedTime()));
        final Set<Map.Entry<RoomSourceDto, Double>> entries = activitySchema.getPreferredTempInLocations().entrySet();
        final long eventDuration = Duration.between(startTime, endTime).toMinutes() * 60;
        final List<DesiredTempSourceDto> results = Lists.newArrayListWithExpectedSize(Math.toIntExact(eventDuration / 5));
        for (int i = 0; i < eventDuration; i += 5) {
            final LocalDateTime currentTime = startTime.plusSeconds(i);
            results.addAll(entries.stream().map(l -> DesiredTempSourceDto.builder().location(l.getKey()).desiredTemp(Precision.round(l.getValue() + weatherConditionsService.calculateInfluenceWeatherOnDesiredTemp(currentTime), 1)).user(user).observationTime(currentTime).build()).collect(Collectors.toList()));
        }
        return results;
    }

    private double getMediaUsageEfficiency(RoomSourceDto location, MediaType mediaType) {
        switch (mediaType) {
            case HOT_WATER:
                return location.getType() == RoomType.BATHROOM ? HOT_WATER_EFF_BATH_PER_MINUTE : HOT_WATER_EFF_KITCHEN_PER_MINUTE;
            case COLD_WATER:
                return location.getType() == RoomType.BATHROOM ? COLD_WATER_EFF_BATH_PER_MINUTE : COLD_WATER_EFF_KITCHEN_PER_MINUTE;
            case GAS:
                return GAS_EFF;
            case ELECTRICITY:
                return ELECTRICITY_EFF;
            default:
                throw new IllegalArgumentException("Efficiency not defined for given Media Type and location");
        }
    }

}
