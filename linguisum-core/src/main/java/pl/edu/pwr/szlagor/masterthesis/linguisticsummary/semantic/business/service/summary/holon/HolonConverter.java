package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon;

import static java.util.stream.Collectors.toList;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-18.
 */
@Component
public class HolonConverter implements Converter<HolonDto, Holon> {

    @Override
    public Holon convert(HolonDto source) {
        return source == null ? null : Holon.builder()
                                            .id(source.getId())
                                            .cardinality(source.getCardinality().get())
                                            .relevance(getRelevance(source))
                                            .predicateType(source.getPredicateType())
                                            .predicate(source.getPredicate())
                                            .predicateString(getPredicate(source))
                                            .parent(convert(source.getParent()))
                                            .cumulatedPredicate(getCumulatedPredicate(source))
                                            .cumulatedPredicatesTypes(source.getCumulatedPredicatesTypes().collect(toList()))
                                            .build();
    }

    private String getCumulatedPredicate(HolonDto source) {
        return source.getCumulatedPredicate() != null ? source.getCumulatedPredicate().toString() : StringUtils.EMPTY;
    }

    private String getPredicate(HolonDto source) {
        return source.getPredicate() != null ? source.getPredicate().toString() : StringUtils.EMPTY;
    }

    private double getRelevance(HolonDto source) {
        return source.getParent() != null && source.getParent().getCardinality().get() > 0
                ? Precision.round(source.getCardinality().doubleValue() / source.getParent().getCardinality().doubleValue(), 2) : 0.00;
    }
}
