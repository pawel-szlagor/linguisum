package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.WeatherEvent;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.TestMySQLConfig;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonPositionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.PersonSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.RoomSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model.WeatherConditionSource;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonPositionSourceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.PersonSourceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.RoomSourceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.repository.WeatherConditionSourceRepository;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.RoomType.BATHROOM;

/**
 * Created by Pawel on 2017-01-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMySQLConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersonPositionSourceRepositoryTest {

    private static final long PERSON_ID = 1L;
    private static final long ROOM_ID = 1L;
    private static final double FALL = 3.0d;
    private static final int HUMIDITY = 80;
    private static final int PRESSURE = 1012;
    private static final float TEMP_OUTSIDE = -5f;
    private static final float WIND_SPEED = 5.6f;
    private static final float SUNLIGHT = 42f;
    private static final long DEVICE_ID = 5L;
    private static final float WIND_CHILL = -7.5f;
    private static final String NAME = "NAME";
    private static final String MAIL = "MAIL";
    private static final String ROOM_NAME = "roomName";

    @Autowired
    private PersonSourceRepository personSourceRepository;
    @Autowired
    private RoomSourceRepository roomSourceRepository;
    @Autowired
    private PersonPositionSourceRepository personPositionSourceRepository;
    @Autowired
    private WeatherConditionSourceRepository weatherConditionRepository;

    @Test
    public void shouldFindPersonPositionSource() {
        // given
        PersonSource person = new PersonSource();
        person.setMail(MAIL);
        person.setName(NAME);
        personSourceRepository.save(person);
        RoomSource room = new RoomSource();
        room.setName(ROOM_NAME);
        room.setType(BATHROOM);
        roomSourceRepository.save(room);
        PersonPositionSource persPos = new PersonPositionSource();
        persPos.setLocation(room);
        persPos.getId().setUser(person);
        persPos.getId().setObservationTime(LocalDateTime.now());
        // when
        persPos = personPositionSourceRepository.save(persPos);
        WeatherConditionSource weatherConditionSource = WeatherConditionSource.builder().weatherEvent(WeatherEvent
                .FOG).humidity(HUMIDITY).observationTime(LocalDateTime.now()).precipitation(10.8f).pressure(PRESSURE)
                .sunlightEmission(SUNLIGHT).tempOut(TEMP_OUTSIDE).windSpeed(WIND_SPEED).windchill(WIND_CHILL).build();
        weatherConditionRepository.save(weatherConditionSource);
        // then
        assertThat(persPos.getId().getUser(), notNullValue());

    }

}