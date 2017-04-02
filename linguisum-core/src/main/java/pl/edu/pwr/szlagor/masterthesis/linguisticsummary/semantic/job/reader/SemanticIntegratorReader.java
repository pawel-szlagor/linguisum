package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.reader;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-02-09.
 */
@Component
@StepScope
public class SemanticIntegratorReader implements ItemReader<Holon>, InitializingBean {

    private final HolonCache holonCache;
    private Integer holonPosition;
    private final List<Holon> holonList;

    @Autowired
    public SemanticIntegratorReader(HolonCache holonCache) {
        this.holonCache = holonCache;
        if (holonPosition != null) {
            holonList = convertToEntites(holonCache.getRootHolons().get(holonPosition)).stream().sorted(comparing(Holon::getLevel)).collect(
                    toList());
        } else {
            holonList = holonCache.getRootHolons()
                                  .stream()
                                  .map(this::convertToEntites)
                                  .flatMap(List::stream)
                                  .sorted(comparing(Holon::getLevel))
                                  .collect(toList());
        }
    }

    @Override
    public Holon read() throws Exception {
        if (!holonList.isEmpty()) {
            return holonList.remove(0);
        }
        return null;
    }

    private List<Holon> convertToEntites(Holon root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        return entites;
    }

    private void addEntityToList(Holon root, List<Holon> entites) {
        entites.add(root);
        if (root.getChildren() != null) {
            root.getChildren().forEach(c -> addEntityToList(c, entites));
        }
    }

    public void setHolonPosition(int holonPosition) {
        this.holonPosition = holonPosition;
    }

    @Override
    public void afterPropertiesSet() {

    }

}
