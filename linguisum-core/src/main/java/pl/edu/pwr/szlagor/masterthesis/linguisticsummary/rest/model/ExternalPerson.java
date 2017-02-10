package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.rest.model;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;

public class ExternalPerson {

    private long id;
    private String name;

    public ExternalPerson() {
    }

    public ExternalPerson(Person person) {
        //this.id = person.id;
        this.name = person.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonDescription [id=" + id + ", name=" + name + "]";
    }

}
