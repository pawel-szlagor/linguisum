package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.SnapshotRepository;

/**
 * Created by Pawel on 2017-03-20.
 */
public class RangePartition implements Partitioner {
    private final SnapshotRepository repository;
    private Long maxItemCount;
    private int pageSize;

    @Autowired
    public RangePartition(SnapshotRepository repository) {
        this.repository = repository;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {

        Map<String, ExecutionContext> result = new HashMap<>();

        final long count = maxItemCount != null ? maxItemCount : repository.count();
        long range = count / gridSize / pageSize;
        long startPage = 0;
        long endPage = range;

        for (int i = 1; i <= gridSize; i++) {
            ExecutionContext value = new ExecutionContext();

            System.out.println("\nStarting : Thread" + i);
            System.out.println("startPage : " + startPage);
            System.out.println("endPage : " + endPage);

            value.putLong("startPage", startPage);
            value.putLong("endPage", endPage);

            // give each thread a name, thread 1,2,3
            value.putString("name", "Thread" + i);

            result.put("partition" + i, value);

            startPage = endPage;
            endPage += range;

        }

        return result;
    }

    public void setMaxItemCount(long maxItemCount) {
        this.maxItemCount = maxItemCount;
    }
}
