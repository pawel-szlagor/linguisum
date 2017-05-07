package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.Declension;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface DeclensionRepository extends JpaRepository<Declension, Long> {
    Declension findByNominative(String nominative);
}
