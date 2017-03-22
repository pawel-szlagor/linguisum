package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.types.expr.DslExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.converter.BooleanExpressionConverter;

/**
 * Created by Pawel on 2017-03-17.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Immutable
@Entity
@Document
public class Holon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;
    @Enumerated(EnumType.STRING)
    private CategoryPredicateTypes predicateType;
    private String predicateString;
    @Convert(converter = BooleanExpressionConverter.class)
    private DslExpression predicate;
    private String cumulatedPredicate;
    @ElementCollection
    private List<CategoryPredicateTypes> cumulatedPredicatesTypes;
    private Long cardinality;
    private double relevance;
    @Embedded
    @IndexedEmbedded
    private Holon parent;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Holon holon = (Holon) o;

        if (id != null ? !id.equals(holon.id) : holon.id != null)
            return false;
        if (predicateType != holon.predicateType)
            return false;
        if (predicate != null ? !predicate.equals(holon.predicate) : holon.predicate != null)
            return false;
        return parent != null ? parent.equals(holon.parent) : holon.parent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (predicateType != null ? predicateType.hashCode() : 0);
        result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
}
