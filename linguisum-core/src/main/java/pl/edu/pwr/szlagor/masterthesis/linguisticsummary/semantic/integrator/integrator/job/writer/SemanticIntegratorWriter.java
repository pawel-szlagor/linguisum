package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.writer;

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

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.HolonConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Paweł on 2017-02-11.
 */
@StepScope
@Component
public class SemanticIntegratorWriter implements ItemWriter<HolonDto>, StepExecutionListener {
    private final MongoTemplate template;
    private HolonDto root;
    private StepExecution stepExecution;
    private HolonConverter converter;

    @Autowired
    public SemanticIntegratorWriter(MongoTemplate template, HolonConverter converter) {
        this.template = template;
        this.converter = converter;
    }

    @Override
    public synchronized void write(List<? extends HolonDto> items) throws Exception {
        // template.insert(items.stream().filter(i->i.getRelevance() > 0.6 || i.getRelevance() < 0.4).collect(toList()),
        // Holon.class);
        if ((Boolean) stepExecution.getExecutionContext().get("readerExhausted")) {
            List<Holon> holonToSave = convertToEntites(items.get(0).getRoot());
            holonToSave.stream().forEach(template::save);
        }
    }

    private List<Holon> convertToEntites(HolonDto root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        stepExecution.getExecutionContext().put("readerExhausted", Boolean.FALSE);
        return entites;
    }

    private void addEntityToList(HolonDto root, List<Holon> entites) {
        if (root.getChildren() != null) {
            entites.add(converter.convert(root));
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
