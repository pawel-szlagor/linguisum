package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

import java.util.List;

/**
 * Created by Pawel on 2017-02-08.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SnapshotSourceDto {
    private ActivityLabelSourceDto label;
    @Singular
    private List<DesiredTempSourceDto> desiredTemps;
    @Singular
    private List<DeviceStateSourceDto> deviceStates;
    @Singular
    private List<MediaUsageSourceDto> mediaUsages;
    @Singular
    private List<PersonPositionSourceDto> personPositions;
    private WeatherConditionSourceDto weatherConditions;
}
