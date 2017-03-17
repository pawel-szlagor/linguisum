package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.similarity;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;

/**
 * Created by Pawel on 2017-03-12.
 */
public interface SimilarityService {

    double calculateSimilarity(SnapshotDto one, SnapshotDto other);

}
