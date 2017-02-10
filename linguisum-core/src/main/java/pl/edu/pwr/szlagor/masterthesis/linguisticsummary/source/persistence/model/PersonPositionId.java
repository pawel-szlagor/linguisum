package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-22.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class PersonPositionId implements Serializable {

    @Column(name = "OBSERVATION_TIME")
    private LocalDateTime observationTime;

    @ManyToOne
    @JoinColumn(name = "ID_USER_FK")
    private PersonSource user;
}
