package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.config;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.tasklet.HolonCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-20.
 */
public class RangePartition implements Partitioner {
    private final SnapshotRepository repository;
    private Integer gridSize;
    private HolonCache holonCache;

    @Autowired
    public RangePartition(SnapshotRepository repository, HolonCache holonCache) {
        this.repository = repository;
        this.holonCache = holonCache;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> result = new HashMap<>();
        final int totalSize = holonCache.getRootHolons()
                                        .stream()
                                        .map(this::convertToEntites)
                                        .flatMap(List::stream)
                                        .sorted(comparing(Holon::getLevel).reversed())
                                        .collect(toList())
                                        .size();

        long range = totalSize / gridSize;
        long startIndex = 0;
        long endIndex = range;

        for (int i = 1; i <= gridSize; i++) {
            ExecutionContext value = new ExecutionContext();

            System.out.println("\nStarting : Thread" + i);
            System.out.println("startIndex : " + startIndex);
            System.out.println("endIndex : " + endIndex);

            value.putLong("startIndex", startIndex);
            value.putLong("endIndex", endIndex);

            // give each thread a name, thread 1,2,3
            value.putString("name", "Thread" + i);

            result.put("partition" + i, value);

            startIndex = endIndex;
            endIndex += range;
            if (i == gridSize) {
                endIndex = totalSize;
            }

        }

        return result;
    }

    private List<Holon> convertToEntites(Holon root) {
        List<Holon> entites = Lists.newArrayListWithExpectedSize(root.count());
        addEntityToList(root, entites);
        return entites;
    }

    private void addEntityToList(Holon root, List<Holon> entites) {
        if (root.getChildren() != null) {
            entites.add(root);
            root.getChildren().forEach(c -> addEntityToList(c, entites));
        }
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
}
