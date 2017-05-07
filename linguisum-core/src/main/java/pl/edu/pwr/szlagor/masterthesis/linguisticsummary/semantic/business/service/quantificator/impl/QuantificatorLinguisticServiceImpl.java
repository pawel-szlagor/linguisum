package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.quantificator.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.QuantificatorLinguisticDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.quantificator.QuantificatorLinguisticService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.QuantificatorLinguistic;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.QuantificatorLinguisticRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-05-05.
 */
@Service
public class QuantificatorLinguisticServiceImpl implements QuantificatorLinguisticService {
    private final QuantificatorLinguisticRepository repository;

    @Autowired
    public QuantificatorLinguisticServiceImpl(QuantificatorLinguisticRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public QuantificatorLinguisticDto save(QuantificatorLinguisticDto QuantificatorLinguisticDto) {
        return getMapperFacade().map(repository.save(getMapperFacade().map(QuantificatorLinguisticDto, QuantificatorLinguistic.class)),
                QuantificatorLinguisticDto.class);
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public void save(Collection<QuantificatorLinguisticDto> QuantificatorLinguisticDtos) {
        repository.save(getMapperFacade().mapAsList(QuantificatorLinguisticDtos, QuantificatorLinguistic.class));
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public QuantificatorLinguisticDto findById(Long aLong) {
        return getMapperFacade().map(repository.findOne(aLong), QuantificatorLinguisticDto.class);
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public String findLabelByValue(Double value) {
        return repository.findByLowerBoundaryGreaterThanAndUpperBoundaryLessThan(value).getLabel();
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public List<QuantificatorLinguisticDto> findAll() {
        return getMapperFacade().mapAsList(repository.findAll(), QuantificatorLinguisticDto.class);
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(QuantificatorLinguisticDto.class, QuantificatorLinguistic.class).byDefault().register();
        factory.classMap(QuantificatorLinguistic.class, QuantificatorLinguisticDto.class).byDefault().register();
        return factory.getMapperFacade();
    }
}
