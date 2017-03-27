package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.math3.util.Precision;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysema.query.collections.GuavaHelpers;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FSnapshotConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.SemanticReadItem;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-08.
 */
@StepScope
@Component
public class SemanticIntegratorProcessor implements ItemProcessor<SemanticReadItem, Holon> {

    private final SnapshotRepository snapshotRepository;
    private final FSnapshotConverter fSnapshotConverter;
    private final HolonCache holonCache;

    @Autowired
    public SemanticIntegratorProcessor(SnapshotRepository snapshotRepository,
            FSnapshotConverter fSnapshotConverter,
            HolonCache holonCache) {
        this.snapshotRepository = snapshotRepository;
        this.fSnapshotConverter = fSnapshotConverter;
        this.holonCache = holonCache;
    }

    @Override
    public Holon process(SemanticReadItem item) throws Exception {
        System.out.println("processing: " + item.getSnapshots().size());
        holonCache.getRootHolons().forEach(r -> r.getCardinality().getAndAdd(item.getSnapshots().size()));
        holonCache.getRootHolons().forEach(r -> r.getChildren().forEach(
                c -> adjustCardinality(c, item.getSnapshots().stream().map(fSnapshotConverter::convert).collect(toList()))));
        return holonCache.getRootHolons().get(0);
    }

    private void adjustCardinality(Holon holon, List<FSnapshot> fSnapshots) {
        try {
            if (holon.getParent() != null && holon.getParent().getCardinality().get() != 0) {
                // final long count = CollQueryFactory.from(QSnapshot.snapshot,
                // snapshots).where(holon.getPredicate()).count();
                final List<FSnapshot> collect = fSnapshots.stream()
                                                          .filter(GuavaHelpers.wrap(holon.getPredicate())::apply)
                                                          .collect(toList());
                // holon.getCardinality().getAndAdd(CollQueryFactory.from(QSnapshot.snapshot,
                // snapshots).where(holon.getPredicate()).count());
                holon.getCardinality().getAndAdd(collect.size());
                holon.setRelevance(holon.getParent() != null && holon.getParent().getCardinality().get() > 0
                        ? Precision.round(holon.getCardinality().doubleValue() / holon.getParent().getCardinality().doubleValue(), 2)
                        : 0.00);
                if (holon.getChildren() != null) {
                    holon.getChildren().forEach(c -> adjustCardinality(c, collect));
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
