package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.levels.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.Getter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.TrapezoidalMemGradeDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.levels.MemGradeService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.MemGradeRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.AbstractService;

/**
 * Created by Pawel on 2017-03-12.
 */
@Getter
@Service
public class MemGradeServiceImpl extends AbstractService<TrapezoidalMemGradeDto, TrapezoidalMemGrade, Long> implements MemGradeService {
    private final MemGradeRepository repository;

    @Autowired
    public MemGradeServiceImpl(MemGradeRepository repository) {
        this.repository = repository;
    }

    @Cacheable
    @Override
    public List<TrapezoidalMemGradeDto> findByPropertyOrdered(String property) {
        final List<TrapezoidalMemGrade> trapezoidalMemGrades = repository.findByPropertyName(property);
        return getMapperFacade().mapAsList(trapezoidalMemGrades, TrapezoidalMemGradeDto.class);
    }

    @Override
    public double calculateDistance(Double one, Double other, String property) {
        return findByPropertyOrdered(property).stream().mapToDouble(l -> Math.abs(l.calculateMembershipGrade(one) - l.calculateMembershipGrade(other))).average().getAsDouble();
    }

    @Override
    protected Class<TrapezoidalMemGrade> getEntityClass() {
        return TrapezoidalMemGrade.class;
    }

    @Override
    protected Class<TrapezoidalMemGradeDto> getDtoClass() {
        return TrapezoidalMemGradeDto.class;
    }
}
