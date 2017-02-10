package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-15.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnapshotDto {

    private ObjectId id;

    private LocalDateTime timestamp;

    private Set<PersonStateDto> personStates;

    private EnvironmentConditions environment;

    private Set<DeviceStateDto> deviceStates;

    private Set<RoomStateDto> roomStates;

    private Set<MediaUsageDto> mediaUsages;

}
