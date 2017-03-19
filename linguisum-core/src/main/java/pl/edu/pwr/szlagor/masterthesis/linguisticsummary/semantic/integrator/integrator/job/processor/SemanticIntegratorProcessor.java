package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.HolonConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class SemanticIntegratorProcessor implements ItemProcessor<HolonDto, Holon> {

    private final SnapshotRepository snapshotRepository;
    private final HolonConverter converter;

    @Autowired
    public SemanticIntegratorProcessor(SnapshotRepository snapshotRepository, HolonConverter converter) {
        this.snapshotRepository = snapshotRepository;
        this.converter = converter;
    }

    @Override
    public Holon process(HolonDto item) throws Exception {
        setCardParent(item);
        setCard(item);
        return converter.convert(item);
    }

    private synchronized void setCard(HolonDto item) {
        if (item.getParent() != null && item.getParent().getCardinality() == 0) {
            item.setCardinality(0L);
        } else if (item.getCardinality() == null) {
            try {
                item.setCardinality(snapshotRepository.count(item.getCumulatedPredicate()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // item.getChildren().forEach(this::setCard);
        }
    }

    private synchronized void setCardParent(HolonDto item) {
        if (item.getParent() != null && item.getParent().getCardinality() == null) {
            try {
                item.getParent().setCardinality(snapshotRepository.count(item.getParent().getCumulatedPredicate()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
