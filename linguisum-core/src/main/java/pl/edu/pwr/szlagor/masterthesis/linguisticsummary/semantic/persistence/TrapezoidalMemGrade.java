package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-03-12.
 */
@Cacheable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TRAPEZOIDAL_MEM_GRADE", indexes = { @Index(columnList = "PROPERTY_NAME") })
public class TrapezoidalMemGrade {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "PROPERTY_NAME")
    private String propertyName;

    @Column(name = "LOWER_BOUNDARY")
    private double lowerBoundary;
    @Column(name = "LOWER_EXTREMUM")
    private double lowerExtremum;
    @Column(name = "UPPER_EXTREMUM")
    private double upperExtremum;
    @Column(name = "UPPER_BOUNDARY")
    private double upperBoundary;

    @Column(name = "DESCRIPTION")
    private String description;
}


