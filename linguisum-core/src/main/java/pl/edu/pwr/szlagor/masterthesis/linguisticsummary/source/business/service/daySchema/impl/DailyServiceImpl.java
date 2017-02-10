package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.impl;

import static java.util.stream.Collectors.toList;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.SampleActivities.AT_WORK;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createDaily(LocalDate date, PersonSourceDto user) {
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
        for (ActivitySchemaSourceDto activitySchema : daily.getActivities()) {
            double mean = activitySchema.getAverageElapsedTime();
            if (AT_WORK.getName().equals(activitySchema.getName())) {
                activitySchema.setAverageElapsedTime(random.randomValueInBorders(mean, mean * 0.25, mean * 0.01));
            } else {
                activitySchema.setAverageElapsedTime(random.randomValueInBorders(mean, mean * 0.25, mean * 0.25));
            }
        }
        final Double totalTime = daily.getActivities().stream().mapToDouble(ActivitySchemaSourceDto::getAverageElapsedTime).sum();
        daily.getActivities().forEach(a -> a.setAverageElapsedTime(a.getAverageElapsedTime() * random.randomValueInBorders(1440, 30, 30) / totalTime));
        daily.calculateSleepHour();
        daily.getActivities().forEach(activitySchemaService::randomizeDaily);
        return daily;
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
