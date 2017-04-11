package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.processor;

import org.springframework.batch.item.ItemProcessor;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.converter.PSnapshotConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-02-08.
 */
public class PSnapshotIntegratorProcessor implements ItemProcessor<Snapshot, PSnapshot> {

    private final PSnapshotConverter converter;

    public PSnapshotIntegratorProcessor(PSnapshotConverter converter) {
        this.converter = converter;
    }

    @Override
    public PSnapshot process(Snapshot item) throws Exception {
        return converter.convert(item);
    }

}
