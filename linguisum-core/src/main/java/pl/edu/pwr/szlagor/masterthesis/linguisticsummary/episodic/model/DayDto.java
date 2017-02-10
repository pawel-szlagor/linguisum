package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-02-08.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DayDto {

    private List<Snapshot> snapshots;
}
