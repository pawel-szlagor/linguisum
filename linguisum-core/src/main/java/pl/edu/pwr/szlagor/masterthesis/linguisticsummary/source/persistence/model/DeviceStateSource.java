package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

/**
 * Created by Pawel on 2017-01-22.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "DEVICE_STATE", indexes = {@Index(columnList = "OBSERVATION_TIME")})
@Entity
public class DeviceStateSource implements Serializable, ObservationTimeAware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OBSERVATION_TIME")
    private LocalDateTime observationTime;

    @ManyToOne
    @JoinColumn(name = "DEVICE_ID")
    private DeviceSource device;

    @Column(name = "IS_ON")
    private boolean isWorking;

}
