package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * Created by Pawel on 2017-01-15.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Document
public class Snapshot {

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;*/

    @Indexed
    @Id
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

    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<RoomState> roomStates;

    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<MediaUsage> mediaUsages;

}
