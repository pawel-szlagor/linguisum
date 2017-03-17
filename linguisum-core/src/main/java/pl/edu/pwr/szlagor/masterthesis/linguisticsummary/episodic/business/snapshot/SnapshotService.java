package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.business.snapshot;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.SnapshotDto;

/**
 * Created by Pawel on 2017-03-07.
 */
public interface SnapshotService {
    @Transactional(readOnly = true)
    SnapshotDto findByLocalDateTime(LocalDateTime LocalDateTime);

    List<SnapshotDto> findAll();
}
