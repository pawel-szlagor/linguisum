package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class SemanticIntegratorWriter implements ItemWriter<Holon> {
    private final MongoTemplate template;

    @Autowired
    public SemanticIntegratorWriter(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public void write(List<? extends Holon> items) throws Exception {
        // template.insert(items.stream().filter(i->i.getRelevance() > 0.6 || i.getRelevance() < 0.4).collect(toList()),
        // Holon.class);
        template.insert(items, "holons");
    }
}
