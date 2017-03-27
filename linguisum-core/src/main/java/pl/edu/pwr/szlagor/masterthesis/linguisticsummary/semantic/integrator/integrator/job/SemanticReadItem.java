package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;

/**
 * Created by Pawel on 2017-03-19.
 */
@AllArgsConstructor
@Data
public class SemanticReadItem {
    private List<Snapshot> snapshots;
}
