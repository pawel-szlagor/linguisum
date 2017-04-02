package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;

/**
 * Created by Pawel on 2017-03-24.
 */
@Getter
@Setter
@AllArgsConstructor
public class ProfilesCombinationsCache {
    private List<List<CategoryPredicateTypes>> profilesCombinations = new ArrayList<>();

    public ProfilesCombinationsCache() {
        this.profilesCombinations = profilesCombinations;
    }
}
