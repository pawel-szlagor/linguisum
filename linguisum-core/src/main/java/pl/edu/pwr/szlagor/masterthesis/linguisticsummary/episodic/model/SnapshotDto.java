package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Pawel on 2017-01-15.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SnapshotDto {

    private LocalDateTime timestamp;

    private Set<PersonStateDto> personStates;

    private EnvironmentConditions environment;

    private Set<DeviceStateDto> deviceStates;

    private Set<RoomStateDto> roomStates;

    private Set<MediaUsageDto> mediaUsages;

}
