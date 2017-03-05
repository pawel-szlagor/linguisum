package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

/**
 * Created by Pawel on 2017-02-08.
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DayDto {

    @Singular
    private List<Snapshot> snapshots;
}
