package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.mongodb.morphia.annotations.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-04-07.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
public class PDevicesStates {

    private DeviceState deviceState1;
    private DeviceState deviceState2;
    private DeviceState deviceState3;
    private DeviceState deviceState4;
    private DeviceState deviceState5;
    private DeviceState deviceState6;
    private DeviceState deviceState7;
    private DeviceState deviceState8;
    private DeviceState deviceState9;
    private DeviceState deviceState10;

}
