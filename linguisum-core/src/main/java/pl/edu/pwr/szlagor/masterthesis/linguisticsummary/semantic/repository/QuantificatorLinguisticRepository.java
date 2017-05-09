package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.QuantificatorLinguistic;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface QuantificatorLinguisticRepository extends JpaRepository<QuantificatorLinguistic, Long> {
    QuantificatorLinguistic findByLowerBoundaryLessThanAndUpperBoundaryGreaterThanEqual(double lower, double upper);
}
