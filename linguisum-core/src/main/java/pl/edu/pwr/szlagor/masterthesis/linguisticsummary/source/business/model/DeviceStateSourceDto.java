package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-29.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStateSourceDto {

    private LocalDateTime observationTime;
    private DeviceSourceDto device;
    private boolean isWorking;
    private RoomSourceDto location;
}
