package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;

/**
 * Created by Pawel on 2017-03-17.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Document
public class Holon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId id;
    @Enumerated(EnumType.STRING)
    private CategoryPredicateTypes predicateType;
    private String predicate;
    private String cumulatedPredicate;
    @ElementCollection
    private List<CategoryPredicateTypes> cumulatedPredicatesTypes;
    private Long cardinality;
    private double relevance;
    @Embedded
    @IndexedEmbedded
    private Holon parent;
}
