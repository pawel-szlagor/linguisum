package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-30.
 */
public class LocalDateConverter extends BidirectionalConverter<LocalDateTime, LocalDateTime> {

    @Override
    public LocalDateTime convertTo(LocalDateTime source, Type<LocalDateTime> type, MappingContext mappingContext) {
        return (LocalDateTime.from(source));
    }

    @Override
    public LocalDateTime convertFrom(LocalDateTime source, Type<LocalDateTime> type, MappingContext mappingContext) {
        return (LocalDateTime.from(source));
    }
}
