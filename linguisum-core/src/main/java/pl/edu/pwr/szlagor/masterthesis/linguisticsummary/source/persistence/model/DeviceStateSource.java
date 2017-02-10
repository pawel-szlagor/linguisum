package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class DeviceStateSource implements Serializable {

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
