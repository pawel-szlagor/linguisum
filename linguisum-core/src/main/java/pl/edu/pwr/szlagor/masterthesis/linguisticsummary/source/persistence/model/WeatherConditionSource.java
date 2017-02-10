package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapping;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-01-22.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "WEATHER")
@Entity
public class WeatherConditionSource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Mapping
    private LocalDateTime observationTime;
    private double tempOut;
    private double windchill;
    private double humidity;
    private int pressure;
    private double windSpeed;
    private double precipitation;
    @Singular
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<WeatherEvent> weatherEvents = new ArrayList<>();
    @Column(name = "SUNLIGHT")
    private double sunlightEmission;

}
