package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER_FK")
    private PersonSource user;
}
