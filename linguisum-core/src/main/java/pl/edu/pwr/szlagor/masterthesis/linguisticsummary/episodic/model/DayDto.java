package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import lombok.*;

import java.util.List;

/**
 * Created by Pawel on 2017-02-08.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DayDto {

    @Singular
    private List<Snapshot> snapshots;
}
