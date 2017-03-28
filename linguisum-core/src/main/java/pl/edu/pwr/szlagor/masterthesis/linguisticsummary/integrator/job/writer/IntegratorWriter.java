package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.writer;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FSnapshotConverter;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class IntegratorWriter implements ItemWriter<Snapshot> {
    private final MongoTemplate template;
    private final FSnapshotConverter converter;

    @Autowired
    public IntegratorWriter(MongoTemplate template, FSnapshotConverter converter) {
        this.template = template;
        this.converter = converter;
    }

    @Override
    public void write(List<? extends Snapshot> items) throws Exception {
        template.insert(items, "snapshot");
        template.insert(items.stream().map(converter::convert).collect(toList()), "fSnapshot");
    }
}
