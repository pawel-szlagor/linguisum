package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Pawel on 2017-01-22.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "MEDIA_USAGE")
@Entity
public class MediaUsageSource implements ObservationTimeAware{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USAGE")
    private Long id;

    private LocalDateTime observationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEDIA_TYPE")
    private MediaType mediaType;

    @Column(name = "USAGE_PER_MINUTE")
    private double usagePerMinute;

    @ManyToOne
    @JoinColumn(name = "LOCATION_ID")
    private RoomSource location;

}
