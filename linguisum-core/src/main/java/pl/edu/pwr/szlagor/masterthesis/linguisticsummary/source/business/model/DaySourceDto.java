package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-02-08.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DaySourceDto {
    private List<ActivitySourceDto> activities;
    private List<DesiredTempSourceDto> desiredTemps;
    private List<DeviceStateSourceDto> deviceStates;
    private List<MediaUsageSourceDto> mediaUsages;
    private List<PersonPositionSourceDto> personPositions;
    private List<WeatherConditionSourceDto> weatherConditions;
}
