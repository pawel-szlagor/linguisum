package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PERSON_POSITION")
@Entity
public class PersonPositionSource implements Serializable {

    @Generated
    @EmbeddedId
    private PersonPositionId id = new PersonPositionId();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LOCATION_ID")
    private RoomSource location;
}
