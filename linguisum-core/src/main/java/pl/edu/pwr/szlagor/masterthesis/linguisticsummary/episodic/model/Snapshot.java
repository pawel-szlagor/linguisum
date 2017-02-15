package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Pawel on 2017-01-15.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Document
@Entity
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;

    @Indexed
    private LocalDateTime timestamp;

    @Singular
    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<PersonState> personStates;

    @Embedded
    private EnvironmentConditions weatherConditions;

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
