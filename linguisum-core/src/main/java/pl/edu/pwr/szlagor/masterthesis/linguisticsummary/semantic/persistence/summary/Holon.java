package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.types.expr.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.common.converter.BooleanExpressionConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;

/**
 * Created by Pawel on 2017-03-17.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Document
public class Holon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId _id;
    @Enumerated(EnumType.STRING)
    private CategoryPredicateTypes predicateType;
    @Convert(converter = BooleanExpressionConverter.class)
    private BooleanExpression predicate;
    private String cumulatedPredicate;
    @ElementCollection
    private List<CategoryPredicateTypes> cumulatedPredicatesTypes;
    private Long cardinality;
    private double relevance;
    private Holon parent;
    @Transient
    private List<Holon> children;

    @Transient
    public BooleanExpression getCumulatedPredicate() {
        return parent != null && parent.getCumulatedPredicate() != null ? parent.getCumulatedPredicate().and(predicate) : predicate;
    }

    @Transient
    public Stream<CategoryPredicateTypes> getCumulatedPredicatesTypes() {
        return parent != null && parent.getCumulatedPredicatesTypes() != null
                ? Stream.concat(parent.getCumulatedPredicatesTypes(), Stream.of(predicateType)) : Stream.of(predicateType);
    }

    @Transient
    public void addChild(BooleanExpression predicate, CategoryPredicateTypes predicateType) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(Holon.builder().parent(this).predicate(predicate).predicateType(predicateType).build());
    }

    @Transient
    public int count() {
        if (children != null) {
            return 1 + children.stream().mapToInt(Holon::count).sum();
        } else {
            return 1;
        }
    }

    @Transient
    public Holon getRoot() {
        return parent == null ? this : parent;
    }

    @Transient
    public void addChildren(List<BooleanExpression> predicates, CategoryPredicateTypes predicateType) {
        final List<Holon> holons = predicates.stream()
                                             .map(p -> Holon.builder()
                                                            .parent(this)
                                                            .predicate(p)
                                                            .predicateType(predicateType)
                                                            .build())
                                             .collect(toList());
        if (children == null) {
            children = holons;
        } else {
            children.addAll(holons);
        }
    }

    @Transient
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Holon holon = (Holon) o;

        if (_id != null ? !_id.equals(holon._id) : holon._id != null)
            return false;
        if (predicateType != holon.predicateType)
            return false;
        if (predicate != null ? !predicate.equals(holon.predicate) : holon.predicate != null)
            return false;
        return parent != null ? parent.equals(holon.parent) : holon.parent == null;
    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (predicateType != null ? predicateType.hashCode() : 0);
        result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
}
