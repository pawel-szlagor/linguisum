package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PSnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class SemanticIntegratorProcessor implements ItemProcessor<Holon, Holon> {

    private final PSnapshotRepository fsnapshotRepository;
    private long counter = 0L;

    @Autowired
    public SemanticIntegratorProcessor(PSnapshotRepository pSnapshotRepository) {
        this.fsnapshotRepository = pSnapshotRepository;
    }

    @Override
    public Holon process(Holon item) throws Exception {
        if (item.getCardinality() != null && item.getCardinality() == 0) {
            return null;
        }
        if (item.getParent() != null && item.getParent().getCardinality() != null && item.getParent().getCardinality() == 0) {
            propagateCardToChildren(item);
        } else if (item.getCardinality() == null && item.getParent().getCardinality() == null) {
            countCardParent(item);
        } else if (item.getCardinality() == null) {
            item.setCardinality(fsnapshotRepository.count(item.getCumulatedPredicate()));
        }
        if (counter % 100 == 0) {
            System.out.println("przetworzono: " + counter);
        }
        counter++;
        return item;
    }

    private void countCardParent(Holon item) {
        if (item.getCardinality() == null && item.getParent().getCardinality() == null) {
            countCardParent(item.getParent());
        }
        item.setCardinality(fsnapshotRepository.count(item.getCumulatedPredicate()));
    }

    private void propagateCardToChildren(Holon item) {
        item.setCardinality(0L);
        if (item.getChildren() != null) {
            item.getChildren().forEach(this::propagateCardToChildren);
        }
    }

}
