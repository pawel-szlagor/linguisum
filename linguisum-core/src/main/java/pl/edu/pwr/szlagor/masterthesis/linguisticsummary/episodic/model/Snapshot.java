package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-15.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    private LocalDateTime timestamp;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<PersonState> personStates;

    @Embedded
    private EnvironmentConditions environment;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<DeviceState> deviceStates;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<RoomState> roomStates;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<MediaUsage> mediaUsages;

}
