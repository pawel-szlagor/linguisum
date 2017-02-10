package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.DeviceType;

/**
 * Created by Pawel on 2017-01-16.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    private Long deviceId;
    private String deviceName;
    private DeviceType deviceType;
}
