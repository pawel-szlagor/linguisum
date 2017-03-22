package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface MemGradeRepository extends JpaRepository<TrapezoidalMemGrade, Long> {
    List<TrapezoidalMemGrade> findByPropertyName(String propertyName);
}
