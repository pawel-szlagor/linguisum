package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.rest;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.rest.model.ExternalPerson;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

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
