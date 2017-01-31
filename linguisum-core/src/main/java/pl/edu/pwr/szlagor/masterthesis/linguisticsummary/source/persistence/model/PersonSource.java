package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pawel on 2017-01-22.
 */

@Getter
@Setter
@NoArgsConstructor
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
