package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ObservationTimeAware;

/**
 * Created by Pawel on 2017-01-22.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Table(name = "MEDIA_USAGE", indexes = {@Index(columnList = "observationTime")})
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
