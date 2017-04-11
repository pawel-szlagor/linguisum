package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.mongodb.morphia.annotations.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-04-07.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
public class PMediaUsages {

    @IndexedEmbedded
    private MediaUsage mediaUsage1;
    @IndexedEmbedded
    private MediaUsage mediaUsage2;
    @IndexedEmbedded
    private MediaUsage mediaUsage3;
    @IndexedEmbedded
    private MediaUsage mediaUsage4;

}
