package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.writer;

import static org.apache.commons.math3.util.Precision.round;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.HolonRepository;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class SemanticIntegratorWriter implements ItemWriter<Holon>, StepExecutionListener {
    private final MongoTemplate template;
    private HolonCache holonCache;
    private StepExecution stepExecution;
    private final HolonRepository repository;

    @Autowired
    public SemanticIntegratorWriter(MongoTemplate template, HolonCache holonCache, HolonRepository repository) {
        this.template = template;
        this.holonCache = holonCache;
        this.repository = repository;
    }

    @Override
    public void write(List<? extends Holon> items) throws Exception {
        // template.insert(items.stream().filter(i->i.getRelevance() > 0.6 || i.getRelevance() < 0.4).collect(toList()),
        // Holon.class);
        holonCache.getRootHolons()
                  .stream()
                  .map(this::convertToEntites)
                  .flatMap(List::stream)
                  .filter(h -> h.getCardinality().get() > 0)
                  .forEach(h -> {
                      h.setRelevance(h.getParent() != null && h.getParent().getCardinality().get() > 0
                              ? round(h.getCardinality().doubleValue() / h.getParent().getCardinality().doubleValue(), 2) : 0.00);
                      template.save(h);
                  });
        System.out.println("zapisuje: ");
    }

    private List<Holon> convertToEntites(Holon root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        // stepExecution.getExecutionContext().put("readerExhausted", Boolean.FALSE);
        return entites;
    }

    private void addEntityToList(Holon root, List<Holon> entites) {
        if (root.getChildren() != null) {
            entites.add(root);
            root.getChildren().forEach(c -> addEntityToList(c, entites));
        }
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
