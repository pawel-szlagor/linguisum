package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.bson.types.ObjectId;

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

    private ObjectId id;

    private LocalDate date;

    private LocalTime time;

    private Set<PersonStateDto> personStates;

    private EnvironmentConditionsDto weatherConditions;

    private Set<DeviceStateDto> deviceStates;

    private Set<RoomStateDto> roomStates;

    private Set<MediaUsageDto> mediaUsages;

}
