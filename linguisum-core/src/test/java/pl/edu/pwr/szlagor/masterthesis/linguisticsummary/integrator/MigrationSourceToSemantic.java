package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Device;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Room;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.DeviceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.integrator.job.config.BatchConfiguration;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.DeviceSourceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonSourceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.RoomSourceRepository;

/**
 * Created by Pawel on 2017-03-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BatchConfiguration.class })
public class MigrationSourceToSemantic {

    @Autowired
    private PersonSourceRepository personSourceRepository;
    @Autowired
    private DeviceSourceRepository deviceSourceRepository;
    @Autowired
    private RoomSourceRepository roomSourceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void shouldImport() {
        personRepository.save(getMapperFacade().mapAsList(personSourceRepository.findAll(), Person.class));
        roomRepository.save(getMapperFacade().mapAsList(roomSourceRepository.findAll(), Room.class));
        deviceRepository.save(getMapperFacade().mapAsList(deviceSourceRepository.findAll(), Device.class));
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        return factory.getMapperFacade();
    }
}
