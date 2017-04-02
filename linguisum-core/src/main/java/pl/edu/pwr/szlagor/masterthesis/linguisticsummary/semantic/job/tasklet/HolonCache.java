package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;

/**
 * Created by Pawel on 2017-03-24.
 */
@Getter
@Setter
@AllArgsConstructor
public class HolonCache {
    private List<Holon> rootHolons = new ArrayList<>();

    public HolonCache() {
        this.rootHolons = rootHolons;
    }
}
