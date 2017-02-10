package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.desiredtemp.impl;

import static org.hamcrest.junit.MatcherAssert.assertThat;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.RoomType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonPositionSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.RoomSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.PersonSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.personposition.PersonPositionSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.RoomSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.TestMySQLConfig;

/**
 * Created by Pawel on 2017-01-31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMySQLConfig.class, loader = AnnotationConfigContextLoader.class)
public class PersonPositionSourceServiceImplIntTest {

    @Autowired
    private PersonPositionSourceService service;

    @Autowired
    private PersonSourceService personSourceService;

    @Autowired
    private RoomSourceService roomSourceService;

    private static final String MAIL = "mail";
    private static final String PERSON_NAME = "personName";
    private static final String ROOM_NAME = "roomName";

    @Test
    public void shouldSavePersonPositionSource() {
        // given
        PersonSourceDto personSourceDto = PersonSourceDto.builder().mail(MAIL).name(PERSON_NAME).build();
        RoomSourceDto roomSourceDto = RoomSourceDto.builder().name(ROOM_NAME).type(RoomType.BATHROOM).build();
        LocalDateTime time = LocalDateTime.now();
        PersonPositionSourceDto dto = PersonPositionSourceDto.builder().location(roomSourceDto).user(personSourceDto)
                .observationTime(time).build();
        // when
        service.save(dto);
        // then
        assertThat(service.findAll(), Matchers.hasSize(1));
    }

}