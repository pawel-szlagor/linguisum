package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-22.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "DEVICE_STATE")
@Entity
public class DeviceStateSource implements Serializable, ObservationTimeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime observationTime;

    @ManyToOne
    @JoinColumn(name = "ID_DEVICE")
    private DeviceSource device;

    @Column(name = "WORK")
    private boolean isWorking;

    @ManyToOne
    @JoinColumn(name = "ID_LOCATION")
    private RoomSource location;
}
