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
@Table(name = "QUANTIFICATOR_LINGUISTIC", indexes = { @Index(columnList = "LABEL") })
public class QuantificatorLinguistic {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LOWER_BOUNDARY")
    private double lowerBoundary;
    @Column(name = "UPPER_BOUNDARY")
    private double upperBoundary;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        QuantificatorLinguistic that = (QuantificatorLinguistic) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "QuantificatorLinguistic{" + "label='" + label + '\'' + '}';
    }
}
