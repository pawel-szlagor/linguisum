package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-29.
 */
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStateSourceDto implements ObservationTimeAware{

    private LocalDateTime observationTime;
    private DeviceSourceDto device;
    private boolean isWorking;
    private RoomSourceDto location;
}
