package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.Predicate;

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
    @Embedded
    private Predicate predicate;
    private String cumulatedPredicate;
    private String linguisticSummary;
    @ElementCollection
    private List<CategoryPredicateTypes> cumulatedPredicatesTypes;
    private Long cardinality;
    private double relevance;
    private Holon parent;
    @Transient
    private List<Holon> children;

    @Transient
    public String getCumulatedPredicateLinguistic() {
        return parent != null && parent.getPredicate() != null
                ? getCumulatedPredicatesFromParents().stream()
                                                     .map(Predicate::toString)
                                                     .reduce(ConjuctionsHipotactic.getRandomConjuction(),
                                                             (a, b) -> String.join(" ",
                                                                     a,
                                                                     Arrays.stream(ConjuctionsHipotactic.values())
                                                                           .map(ConjuctionsHipotactic::getValue)
                                                                           .anyMatch(e -> e.equals(a)) ? ""
                                                                                   : Conjuctions.getRandomConjuction(),
                                                                     b))
                : "";
    }

    @Transient
    public String getAssumption() {
        return predicate != null ? predicate.toString() : "";
    }

    @Transient
    public BooleanExpression getCumulatedPredicate() {
        final List<Predicate> cumulatedPredicates = getCumulatedPredicatesFromParents();
        cumulatedPredicates.add(predicate);
        return cumulatedPredicates.stream().map(Predicate::getBooleanExpression).reduce(BooleanExpression::and).orElse(null);
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
    public List<Predicate> getCumulatedPredicatesFromParents() {
        if (parent != null && parent.getPredicate() != null) {
            final List<Predicate> cumulatedPredicatesFromParents = parent.getCumulatedPredicatesFromParents();
            cumulatedPredicatesFromParents.add(parent.getPredicate());
            return cumulatedPredicatesFromParents;
        } else {
            return Lists.newArrayList();
        }
    }

    @Transient
    public Holon getRoot() {
        return parent == null ? this : parent;
    }

    @Transient
    public void addChildren(List<Predicate> predicates, CategoryPredicateTypes predicateType) {
        final List<Holon> holons = predicates.stream()
                                             .map(p -> Holon.builder().parent(this).predicate(p).predicateType(predicateType).build())
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
