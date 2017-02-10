package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * Created by Pawel on 2017-02-02.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySourceDto {
    private String name;
    private LocalDateTime startTime;
    @Singular
    private Set<PersonPositionSourceDto> personPositionSourceDtos;
    @Singular
    private Set<DesiredTempSourceDto> desiredTempSourceDtos;
    private PersonSourceDto personSourceDto;
    @Singular
    private Set<DeviceStateSourceDto> deviceStateSourceDtos;
    @Singular
    private Set<MediaUsageSourceDto> mediaUsageSourceDtos;
    @Singular
    private Set<ActivityLabelSourceDto> activityLabelSourceDtos;
}
