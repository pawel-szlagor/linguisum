package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.writer;

import static org.apache.commons.math3.util.Precision.round;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.HolonRepository;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class SemanticIntegratorWriter implements ItemWriter<Holon>, StepExecutionListener {
    private static final Predicate<Holon> PROCESSED_PREDICATE = h -> h.getParent() != null && h.getParent().getCardinality() != null
            && h.getCardinality() != null && h.getCardinality() > 0;
    private final MongoTemplate template;
    private HolonCache holonCache;
    private StepExecution stepExecution;
    private final HolonRepository repository;
    private long counter = 0L;

    @Autowired
    public SemanticIntegratorWriter(MongoTemplate template, HolonCache holonCache, HolonRepository repository) {
        this.template = template;
        this.holonCache = holonCache;
        this.repository = repository;
    }

    @Override
    public void write(List<? extends Holon> items) throws Exception {
        items.forEach(h -> {
            h.setRelevance(h.getParent() != null && h.getParent().getCardinality() > 0
                    ? round(h.getCardinality().doubleValue() / h.getParent().getCardinality().doubleValue(), 2) : 0.00);
            Query query = Query.query(Criteria.where("_id").is(h.get_id()));
            Update update = Update.update("cardinality", h.getCardinality());
            Update updateRelevance = Update.update("relevance", h.getRelevance());
            template.upsert(query, update, Holon.class);
            template.upsert(query, updateRelevance, Holon.class);
            if (counter % 10000 == 0) {
                System.out.println("Update: " + counter);
            }
            counter++;
        });
        // System.out.println("zapisuje: ");
    }

    private List<Holon> convertToEntites(Holon root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        // stepExecution.getExecutionContext().put("readerExhausted", Boolean.FALSE);
        return entites;
    }

    private void addEntityToList(Holon root, List<Holon> entites) {
        entites.add(root);
        if (root.getChildren() != null) {
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
