package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "DESIRED_TEMP", indexes = {@Index(columnList = "observationTime")})
@Entity
public class DesiredTempSource implements ObservationTimeAware{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime observationTime;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private PersonSource user;

    private double desiredTemp;

    @ManyToOne
    @JoinColumn(name = "ID_LOCATION")
    private RoomSource location;
}
