package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity.SampleActivities.SLEEPING;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * Created by Pawel on 2017-02-01.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DaySchemaSourceDto {
    private boolean workingDay;
    private LocalTime wakingHour;
    private PersonSourceDto personSourceDto;
    @Singular
    private List<DayOfWeek> dayOfWeeks;

    @Singular
    private List<ActivitySchemaSourceDto> activities;

    public LocalTime calculateSleepHour() {
        Double period = activities.stream().filter(a -> !SLEEPING.getName().equals(a.getName())).map(ActivitySchemaSourceDto
                ::getAverageElapsedTime).reduce((a, b) -> a + b).get();
        return wakingHour.plusMinutes(period.longValue());
    }

}
