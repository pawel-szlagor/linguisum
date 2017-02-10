package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import lombok.Getter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.PersonSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonSourceRepository;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Transactional(readOnly = true)
@Service
public class PersonSourceServiceImpl extends AbstractService<PersonSourceDto, PersonSource, Long> implements
        PersonSourceService {

    private final PersonSourceRepository repository;

    @Autowired
    public PersonSourceServiceImpl(PersonSourceRepository repository) {
        this.repository = repository;
    }

    @Caching
    @Override
    public PersonSourceDto findByName(String name) {
        Assert.notNull(name);
        PersonSource personSource = getRepository().findByName(name);
        return getMapperFacade().map(personSource, getDtoClass());
    }

    @Override
    protected Class<PersonSource> getEntityClass() {
        return PersonSource.class;
    }

    @Override
    protected Class<PersonSourceDto> getDtoClass() {
        return PersonSourceDto.class;
    }
}
