package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Created by Pawel on 2017-01-16.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeviceState {

    private Device deviceId;
    private Room roomId;
    private boolean isOn;

}
