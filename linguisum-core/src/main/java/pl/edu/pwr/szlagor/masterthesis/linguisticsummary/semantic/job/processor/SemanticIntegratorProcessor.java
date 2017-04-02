package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.FSnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class SemanticIntegratorProcessor implements ItemProcessor<Holon, Holon> {

    private final FSnapshotRepository fsnapshotRepository;
    private long counter = 0L;

    @Autowired
    public SemanticIntegratorProcessor(FSnapshotRepository fsnapshotRepository) {
        this.fsnapshotRepository = fsnapshotRepository;
    }

    @Override
    public Holon process(Holon item) throws Exception {
        if (item.getParent() != null && item.getParent().getCardinality() != null && item.getParent().getCardinality() == 0) {
            propagateCardToChildren(item);
        } else {
            item.setCardinality(fsnapshotRepository.count(item.getCumulatedPredicate()));
        }
        if (counter % 100 == 0) {
            System.out.println("przetworzono: " + counter);
        }
        counter++;
        return item;
    }

    private void propagateCardToChildren(Holon item) {
        item.setCardinality(0L);
        if (item.getChildren() != null) {
            item.getChildren().forEach(this::propagateCardToChildren);
        }
    }

}
