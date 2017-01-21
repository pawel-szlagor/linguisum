package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Created by Pawel on 2017-01-16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeviceState {

    private Long deviceId;
    private Long roomId;
    private boolean isOn;

}
