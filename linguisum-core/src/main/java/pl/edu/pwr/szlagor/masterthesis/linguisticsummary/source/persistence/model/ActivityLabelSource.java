package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-02-05.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "ACTIVITY_LABEL")
@Entity
public class ActivityLabelSource {
    @Id
    @GeneratedValue
    private long id;
    private String label;
    private LocalDateTime observationTime;
}
