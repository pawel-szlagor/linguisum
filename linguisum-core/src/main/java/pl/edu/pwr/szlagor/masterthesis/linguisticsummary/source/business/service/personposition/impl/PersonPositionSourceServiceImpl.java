package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition
        .PersonPositionSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionId;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonPositionSourceRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Service
public class PersonPositionSourceServiceImpl extends AbstractService<PersonPositionSourceDto, PersonPositionSource,
        PersonPositionId> implements PersonPositionSourceService {

    private final PersonPositionSourceRepository repository;

    @Autowired
    public PersonPositionSourceServiceImpl(PersonPositionSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Class<PersonPositionSource> getEntityClass() {
        return PersonPositionSource.class;
    }

    @Override
    protected Class<PersonPositionSourceDto> getDtoClass() {
        return PersonPositionSourceDto.class;
    }
}
