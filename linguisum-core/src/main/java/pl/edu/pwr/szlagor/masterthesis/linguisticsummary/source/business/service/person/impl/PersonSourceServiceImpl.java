package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.PersonSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonSourceRepository;

import javax.transaction.Transactional;

/**
 * Created by Pawel on 2017-01-29.
 */
@Getter
@Transactional
@Service
public class PersonSourceServiceImpl extends AbstractService<PersonSourceDto, PersonSource, Long> implements
        PersonSourceService {

    private final PersonSourceRepository repository;

    @Autowired
    public PersonSourceServiceImpl(PersonSourceRepository repository) {
        this.repository = repository;
    }

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
