package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStateDto {

    private Long deviceId;
    private Long roomId;
    private boolean isOn;

}
