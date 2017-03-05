package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;
import java.util.Set;

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
public class SnapshotSourceDto {
    private LocalDateTime observationTime;
    private ActivityLabelSourceDto label;
    @Singular
    private Set<DesiredTempSourceDto> desiredTemps;
    @Singular
    private Set<DeviceStateSourceDto> deviceStates;
    @Singular
    private Set<MediaUsageSourceDto> mediaUsages;
    @Singular
    private Set<PersonPositionSourceDto> personPositions;
    private WeatherConditionSourceDto weatherConditions;
}
