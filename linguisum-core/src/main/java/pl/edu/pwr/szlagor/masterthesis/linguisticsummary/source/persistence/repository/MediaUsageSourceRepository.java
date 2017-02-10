package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.MediaUsageSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.RoomSource;

/**
 * Created by Pawel on 2017-01-22.
 */
public interface MediaUsageSourceRepository extends JpaRepository<MediaUsageSource, Long> {

    MediaUsageSource findByUsagePerMinuteAndLocation(double usagePerMinute, RoomSource location);
}
