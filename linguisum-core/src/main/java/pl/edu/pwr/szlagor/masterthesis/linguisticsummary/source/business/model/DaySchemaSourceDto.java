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
import lombok.ToString;

/**
 * Created by Pawel on 2017-02-01.
 */
@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DaySchemaSourceDto {
    private boolean workingDay;
    private LocalTime wakingHour;
    private LocalTime sleepHour;
    private PersonSourceDto personSourceDto;
    @Singular
    private List<DayOfWeek> dayOfWeeks;

    @Singular
    private List<ActivitySchemaSourceDto> activities;

    public LocalTime calculateSleepHour() {
        Double period = activities.stream().filter(a -> !SLEEPING.build().getName().equals(a.getName())).mapToDouble(ActivitySchemaSourceDto
                                                                                                                             ::getAverageElapsedTime).sum();
        return wakingHour.plusMinutes(period.longValue());
    }

}
