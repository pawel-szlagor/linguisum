package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.impl;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.SampleActivities.AT_WORK;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DaySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.ActivitySchemaService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.ActivityService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.DailySchemaToActivitiesConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.DailyService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.SampleDailies;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.distribution.NormalDistributionGenerator;

/**
 * Created by Pawel on 2017-02-01.
 */
@Service
public class DailyServiceImpl implements DailyService {
    private final ActivitySchemaService activitySchemaService;
    private final NormalDistributionGenerator random;
    private final DailySchemaToActivitiesConverter converter;
    private final ActivityService activityService;

    @Autowired
    public DailyServiceImpl(ActivitySchemaService activitySchemaService, NormalDistributionGenerator random, DailySchemaToActivitiesConverter converter, ActivityService activityService) {
        this.activitySchemaService = activitySchemaService;
        this.random = random;
        this.converter = converter;
        this.activityService = activityService;
    }

    @Override
    public synchronized void createDaily(LocalDate date, PersonSourceDto user) {
        final List<DaySchemaSourceDto> dailies = getDailySchemas().stream().filter(l -> l.getDayOfWeeks().contains(date.getDayOfWeek())).collect(Collectors.toList());
        DaySchemaSourceDto daily = dailies.get(Math.toIntExact(random.nextInt(dailies.size())));
        daily = randomizeDaily(daily);
        daily.setPersonSourceDto(user);
        List<ActivitySourceDto> activitySourceDtos = converter.convert(daily, date);
        activityService.save(activitySourceDtos);
    }

    @Override
    public synchronized DaySchemaSourceDto randomizeDaily(DaySchemaSourceDto daily) {
        LocalTime currentTime = daily.getWakingHour().plusMinutes((long) random.randomValueInBorders(0, 60, 30)).plusSeconds(random.nextInt(60));
        daily.setWakingHour(currentTime);
        LocalTime sleepTime = daily.getSleepHour().plusMinutes((long) random.randomValueInBorders(0, 20, 5)).plusSeconds(random.nextInt(60));
        daily.setSleepHour(sleepTime.getHour() > 21 ? sleepTime : LocalTime.MAX.minusSeconds(5));
        for (ActivitySchemaSourceDto activitySchema : daily.getActivities()) {
            double mean = activitySchema.getAverageElapsedTime();
            if (AT_WORK.build().getName().equals(activitySchema.getName())) {
                activitySchema.setAverageElapsedTime(random.randomValueInBorders(mean, mean * 0.25, mean * 0.01));
            } else {
                activitySchema.setAverageElapsedTime(random.randomValueInBorders(mean, mean * 0.25, mean * 0.25));
            }
        }
        double dayLength = normalizeDayLength(daily, currentTime, daily.getSleepHour());
        while (daily.calculateSleepHour().getHour() < 21) {
            dayLength = normalizeDayLength(daily, currentTime, daily.getSleepHour());
            System.out.println("normalizing: " + currentTime + "sleepTime: " + sleepTime);
        }
        Assert.isTrue(daily.calculateSleepHour().getHour() > 21, "Time was: " + daily.calculateSleepHour() + "sleep Time was: " + sleepTime);
        Assert.isTrue(dayLength > 0, "Day lentght negative: " + dayLength);
        daily.getActivities().forEach(activitySchemaService::randomizeDaily);
        return daily;
    }

    private double normalizeDayLength(DaySchemaSourceDto daily, LocalTime currentTime, LocalTime sleepTime) {
        final double activitiesDuration = daily.getActivities().stream().mapToDouble(ActivitySchemaSourceDto::getAverageElapsedTime).sum();
        final double dayLength = Duration.between(currentTime, sleepTime).toMinutes();
        daily.getActivities().forEach(a -> a.setAverageElapsedTime(Math.round(a.getAverageElapsedTime() * dayLength / activitiesDuration)));
        return dayLength;
    }

    private List<DaySchemaSourceDto> getDailySchemas() {
        Field[] fields = SampleDailies.class.getFields();
        return Arrays.stream(fields).filter(field -> field.getType().equals(DaySchemaSourceDto.class)).map(f -> {
            try {
                return (DaySchemaSourceDto) f.get(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(toList());
    }


}
