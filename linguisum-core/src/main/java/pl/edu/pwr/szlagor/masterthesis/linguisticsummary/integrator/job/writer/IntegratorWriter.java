package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

import java.util.List;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class IntegratorWriter implements ItemWriter<Snapshot> {
    private final MongoTemplate template;

    @Autowired
    public IntegratorWriter(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public void write(List<? extends Snapshot> items) throws Exception {
        template.insert(items, "snapshot");
    }
}
