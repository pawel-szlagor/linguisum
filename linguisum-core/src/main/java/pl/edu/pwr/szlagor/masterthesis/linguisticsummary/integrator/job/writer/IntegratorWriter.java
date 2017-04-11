package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.writer;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.converter.PSnapshotConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class IntegratorWriter implements ItemWriter<Snapshot> {
    private final MongoTemplate template;
    private final PSnapshotConverter converter;

    @Autowired
    public IntegratorWriter(MongoTemplate template, PSnapshotConverter converter) {
        this.template = template;
        this.converter = converter;
    }

    @Override
    public void write(List<? extends Snapshot> items) throws Exception {
        template.insert(items, "snapshot");
        template.insert(items.stream().map(converter::convert).collect(toList()), "pSnapshot");
    }
}
