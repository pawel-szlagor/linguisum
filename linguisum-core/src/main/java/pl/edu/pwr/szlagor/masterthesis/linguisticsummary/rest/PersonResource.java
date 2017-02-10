package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.rest.model.ExternalPerson;

@Path("/persons")
public class PersonResource {

    @Inject
    private PersonRepository personRepository;

    public PersonResource() {
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public List<ExternalPerson> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<ExternalPerson> externalPersons = new ArrayList<>(persons.size());

        for (Person person : persons) {
            externalPersons.add(new ExternalPerson(person));
        }

        return externalPersons;
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public long createPerson(ExternalPerson externalPerson) {
        Person person = new Person(externalPerson.getName());
        person = personRepository.save(person);
        return 1L;
    }
}
