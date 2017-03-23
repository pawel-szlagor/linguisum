package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.MemGradeRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-03-12.
 */
@Getter
@Service
public class MemGradeServiceImpl implements MemGradeService {
    private final MemGradeRepository repository;

    @Autowired
    public MemGradeServiceImpl(MemGradeRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "memGrade")
    @Override
    public List<TrapezoidalMemGrade> findByProperty(String property) {
        return repository.findByPropertyName(property);
    }

    @Override
    public double calculateDistance(Double one, Double other, String property) {
        return findByProperty(property).stream()
                                       .mapToDouble(l -> Math.abs(l.calculateMembershipGrade(one) - l.calculateMembershipGrade(other)))
                                       .average()
                                       .getAsDouble();
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public TrapezoidalMemGradeDto save(TrapezoidalMemGradeDto trapezoidalMemGradeDto) {
        return getMapperFacade().map(repository.save(getMapperFacade().map(trapezoidalMemGradeDto, TrapezoidalMemGrade.class)),
                TrapezoidalMemGradeDto.class);
    }

    @Override
    @Transactional(value = "semanticTransactionManager")
    public void save(Collection<TrapezoidalMemGradeDto> trapezoidalMemGradeDtos) {
        repository.save(getMapperFacade().mapAsList(trapezoidalMemGradeDtos, TrapezoidalMemGrade.class));
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public TrapezoidalMemGradeDto findById(Long aLong) {
        return getMapperFacade().map(repository.findOne(aLong), TrapezoidalMemGradeDto.class);
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public List<TrapezoidalMemGradeDto> findAll() {
        return getMapperFacade().mapAsList(repository.findAll(), TrapezoidalMemGradeDto.class);
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public List<TrapezoidalMemGradeDto> findAllInBulk() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, value = "semanticTransactionManager")
    public void saveInBulk(Collection<TrapezoidalMemGradeDto> collection) {

    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(TrapezoidalMemGradeDto.class, TrapezoidalMemGrade.class).byDefault().register();
        factory.classMap(TrapezoidalMemGrade.class, TrapezoidalMemGradeDto.class).byDefault().register();
        return factory.getMapperFacade();
    }
}
