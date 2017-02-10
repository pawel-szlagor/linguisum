package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-01-22.
 */
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Immutable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "USER")
@Entity
public class PersonSource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    private Long id;

    private String name;

    private String mail;
}
