package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import com.mysema.query.types.expr.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;

/**
 * Created by Pawel on 2017-03-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "children")
public class HolonDto {
    private CategoryPredicateTypes predicateType;
    private BooleanExpression predicate;
    private AtomicLong cardinality;
    private HolonDto parent;
    private List<HolonDto> children = new ArrayList<>();

    public BooleanExpression getCumulatedPredicate() {
        return parent != null && parent.getCumulatedPredicate() != null ? parent.getCumulatedPredicate().and(predicate) : predicate;
    }

    public Stream<CategoryPredicateTypes> getCumulatedPredicatesTypes() {
        return parent != null && parent.getCumulatedPredicatesTypes() != null
                ? Stream.concat(parent.getCumulatedPredicatesTypes(), Stream.of(predicateType)) : Stream.of(predicateType);
    }

    public void addChild(BooleanExpression predicate, CategoryPredicateTypes predicateType) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(HolonDto.builder().parent(this).predicate(predicate).predicateType(predicateType).build());
    }

    public int count() {
        if (children != null) {
            return 1 + children.stream().mapToInt(HolonDto::count).sum();
        } else {
            return 1;
        }
    }

    public HolonDto getRoot() {
        return parent == null ? this : parent;
    }

    public void addChildren(List<BooleanExpression> predicates, CategoryPredicateTypes predicateType) {
        final List<HolonDto> holonDtos = predicates.stream()
                                                   .map(p -> HolonDto.builder()
                                                                     .parent(this)
                                                                     .cardinality(new AtomicLong(0))
                                                                     .predicate(p)
                                                                     .predicateType(predicateType)
                                                                     .build())
                                                   .collect(toList());
        if (children == null) {
            children = holonDtos;
        } else {
            children.addAll(holonDtos);
        }
    }

    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }
}
