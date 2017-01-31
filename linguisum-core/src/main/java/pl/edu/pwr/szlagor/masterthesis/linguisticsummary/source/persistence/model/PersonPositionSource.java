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
@Table(name = "PERSON_POSITION")
@Entity
public class PersonPositionSource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "OBSERVATION_TIME")
    private LocalDateTime observationTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER_FK")
    private PersonSource user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private RoomSource location;
}
