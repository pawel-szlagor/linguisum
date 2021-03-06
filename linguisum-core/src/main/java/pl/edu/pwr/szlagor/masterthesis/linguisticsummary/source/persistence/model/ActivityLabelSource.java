package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

/**
 * Created by Pawel on 2017-02-05.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "ACTIVITY_LABEL", indexes = {@Index(columnList = "OBSERVATION_TIME")})
@Entity
public class ActivityLabelSource implements ObservationTimeAware{
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "OBSERVATION_TIME")
    private LocalDateTime observationTime;
}
