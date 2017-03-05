package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/**
 * Created by Pawel on 2017-02-08.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DaySourceDto {
    @Singular
    private Map<LocalDateTime, List<ActivityLabelSourceDto>> labels;
    @Singular
    private Map<LocalDateTime, List<DesiredTempSourceDto>> desiredTemps;
    @Singular
    private Map<LocalDateTime, List<DeviceStateSourceDto>> deviceStates;
    @Singular
    private Map<LocalDateTime, List<MediaUsageSourceDto>> mediaUsages;
    @Singular
    private Map<LocalDateTime, List<PersonPositionSourceDto>> personPositions;
    @Singular
    private List<WeatherConditionSourceDto> weatherConditions;
}
