package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysema.query.collections.CollQueryFactory;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.HolonConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.SemanticReadItem;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class SemanticIntegratorProcessor implements ItemProcessor<SemanticReadItem, HolonDto> {

    private final SnapshotRepository snapshotRepository;
    private final HolonConverter converter;

    @Autowired
    public SemanticIntegratorProcessor(SnapshotRepository snapshotRepository, HolonConverter converter) {
        this.snapshotRepository = snapshotRepository;
        this.converter = converter;
    }

    @Override
    public HolonDto process(SemanticReadItem item) throws Exception {
        final HolonDto root = item.getRoot();
        root.getChildren().forEach(c -> adjustCardinality(c, item.getSnapshots()));
        return root;
    }

    private void adjustCardinality(HolonDto holon, List<Snapshot> snapshots) {
        try {
            if (holon.getParent() != null && holon.getParent().getCardinality().get() != 0) {
                // final long count = CollQueryFactory.from(QSnapshot.snapshot,
                // snapshots).where(holon.getPredicate()).count();
                // final List<Snapshot> collect =
                // snapshots.stream().filter(wrap(holon.getPredicate())::apply).collect(toList());
                holon.getCardinality().getAndAdd(CollQueryFactory.from(QSnapshot.snapshot, snapshots).where(holon.getPredicate()).count());
                if (holon.getChildren() != null) {
                    holon.getChildren().forEach(c -> adjustCardinality(c, snapshots));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * private void adjustCardinality(HolonDto holon, List<Snapshot> snapshots) {
     * try {
     * //final List<Snapshot> collect = snapshots.stream().filter(s->from(QSnapshot.snapshot,
     * s).where(holon.getPredicate()).exists()).collect(toList());
     * final List<Snapshot> collect =
     * snapshots.stream().filter(GuavaHelpers.wrap(holon.getPredicate())::apply).collect(toList());
     * if (!collect.isEmpty() && holon.getChildren() != null) {
     * holon.getCardinality().getAndAdd(collect.size());
     * holon.getChildren().forEach(c -> adjustCardinality(c, collect));
     * }
     * }catch (Exception ex){
     * ex.printStackTrace();
     * }
     * }
     */

    private synchronized void setCard(HolonDto item) {
        if (item.getParent() != null && item.getParent().getCardinality().get() == 0) {
            item.setCardinality(new AtomicLong(0));
        } else if (item.getCardinality() == null) {
            try {
                item.setCardinality(new AtomicLong(snapshotRepository.count(item.getCumulatedPredicate())));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // item.getChildren().forEach(this::setCard);
        }
    }

    private synchronized void setCardParent(HolonDto item) {
        if (item.getParent() != null && item.getParent().getCardinality() == null) {
            try {
                item.getParent().setCardinality(new AtomicLong(snapshotRepository.count(item.getParent().getCumulatedPredicate())));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
