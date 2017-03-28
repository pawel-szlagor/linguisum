package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface MemGradeRepository extends JpaRepository<TrapezoidalMemGrade, Long> {
    List<TrapezoidalMemGrade> findByPropertyName(String propertyName);
}
