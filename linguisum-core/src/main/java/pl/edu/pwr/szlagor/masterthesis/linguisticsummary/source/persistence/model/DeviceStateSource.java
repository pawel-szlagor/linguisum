package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DEVICE_STATE")
@Entity
public class DeviceStateSource implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/

    private LocalDateTime observationTime;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_DEVICE")
    private DeviceSource device;

    @Column(name = "WORK")
    private boolean isWorking;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_LOCATION")
    private RoomSource location;
}
