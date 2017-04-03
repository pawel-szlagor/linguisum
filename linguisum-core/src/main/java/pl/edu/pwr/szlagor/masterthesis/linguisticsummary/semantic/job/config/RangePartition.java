package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet.ProfilesCombinationsCache;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-20.
 */
public class RangePartition implements Partitioner {
    private final SnapshotRepository repository;
    private Integer gridSize;
    private ProfilesCombinationsCache profilesCombinationsCache;

    @Autowired
    public RangePartition(SnapshotRepository repository, ProfilesCombinationsCache profilesCombinationsCache) {
        this.repository = repository;
        this.profilesCombinationsCache = profilesCombinationsCache;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> result = new HashMap<>();
        final int totalSize = profilesCombinationsCache.getProfilesCombinations().size();

        int range = totalSize / gridSize;
        int startIndex = 0;
        int endIndex = range;

        for (int i = 1; i <= gridSize; i++) {
            ExecutionContext value = new ExecutionContext();

            System.out.println("\nStarting : Thread" + i);
            System.out.println("startIndex : " + startIndex);
            System.out.println("endIndex : " + endIndex);

            value.putInt("startIndex", startIndex);
            value.putInt("endIndex", endIndex);

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
