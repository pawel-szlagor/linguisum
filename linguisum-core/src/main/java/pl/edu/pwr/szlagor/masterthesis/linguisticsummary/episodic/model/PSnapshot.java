package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

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

/**
 * Created by Pawel on 2017-01-15.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Document(collection = "psnapshot")
@Entity
public class PSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;

    @Indexed
    private int date;

    @Indexed
    private int dayOfWeek;

    @Indexed
    private int time;

    private Person person;

    @IndexedEmbedded
    @Cascade(CascadeType.ALL)
    private PersonState personStates;

    @IndexedEmbedded
    @Embedded
    private EnvironmentConditions weatherConditions;

    @IndexedEmbedded
    @Embedded
    @Cascade(CascadeType.ALL)
    private PDevicesStates deviceStates;

    @IndexedEmbedded
    @Embedded
    @Cascade(CascadeType.ALL)
    private PRoomsStates roomStates;

    @IndexedEmbedded
    @Embedded
    @Cascade(CascadeType.ALL)
    private PMediaUsages mediaUsages;

}
