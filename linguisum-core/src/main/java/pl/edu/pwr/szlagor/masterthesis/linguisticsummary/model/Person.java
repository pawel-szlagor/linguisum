package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String name;

/*	@OneToMany
    public Set<Hike> organizedHikes = new HashSet<>();*/


    public Person(String name) {
        this.name = name;
    }


}
