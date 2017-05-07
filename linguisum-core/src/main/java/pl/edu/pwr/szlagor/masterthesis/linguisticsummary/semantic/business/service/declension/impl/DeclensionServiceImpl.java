package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.declension.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.DeclensionDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.declension.DeclensionService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.Declension;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.DeclensionRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-05-05.
 */
@Service
public class DeclensionServiceImpl implements DeclensionService {
    private final DeclensionRepository repository;

    @Autowired
    public DeclensionServiceImpl(DeclensionRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public DeclensionDto save(DeclensionDto DeclensionDto) {
        return getMapperFacade().map(repository.save(getMapperFacade().map(DeclensionDto, Declension.class)), DeclensionDto.class);
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public void save(Collection<DeclensionDto> DeclensionDtos) {
        repository.save(getMapperFacade().mapAsList(DeclensionDtos, Declension.class));
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public DeclensionDto findById(Long aLong) {
        return getMapperFacade().map(repository.findOne(aLong), DeclensionDto.class);
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public DeclensionDto findByNominative(String nominative) {
        return getMapperFacade().map(repository.findByNominative(nominative), DeclensionDto.class);

    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public List<DeclensionDto> findAll() {
        return getMapperFacade().mapAsList(repository.findAll(), DeclensionDto.class);
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(DeclensionDto.class, Declension.class).byDefault().register();
        factory.classMap(Declension.class, DeclensionDto.class).byDefault().register();
        return factory.getMapperFacade();
    }
}
