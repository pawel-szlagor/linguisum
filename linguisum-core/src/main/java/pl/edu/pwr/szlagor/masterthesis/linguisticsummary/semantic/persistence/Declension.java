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
@Table(name = "DECLENSION", indexes = { @Index(columnList = "NOMINATIVE") })
public class Declension {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "NOMINATIVE")
    private String nominative;
    @Column(name = "GENITIVE")
    private String genitive;
    @Column(name = "DATIVE")
    private String dative;
    @Column(name = "ACCUSATIVE")
    private String accusative;
    @Column(name = "INSTRUMENTAL")
    private String instrumental;
    @Column(name = "LOCATIVE")
    private String locative;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Declension that = (Declension) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "QuantificatorLinguistic{" + "nominative='" + nominative + '\'' + '}';
    }
}
