package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;

/**
 * Created by Pawel on 2017-01-15.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Document
@Entity
public class FSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    @Indexed
    private LocalDate date;
    @Indexed
    private LocalTime time;
    @Singular
    @IndexedEmbedded
    @ElementCollection
    @Cascade(CascadeType.ALL)
    private Set<PersonState> personStates;
    @IndexedEmbedded
    @Embedded
    private FEnvironmentConditions weatherConditions;
    @Singular
    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<DeviceState> deviceStates;
    @Singular
    @IndexedEmbedded(includeEmbeddedObjectId = true, prefix = "_")
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<FRoomState> roomStates;
    @Singular
    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    @ElementCollection
    private Set<FMediaUsage> mediaUsages;

}
