package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.daySchema.impl.DailyServiceImpl;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.label.ActivityLabelService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person.PersonSourceService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.config.TestMySQLConfig;

/**
 * Created by Pawel on 2017-02-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableCaching
@Transactional
@ContextConfiguration(classes = TestMySQLConfig.class, loader = AnnotationConfigContextLoader.class)
public class DaySchemaSourceDtoServiceImplIntTest {

    private static final LocalDate FIRST_DAY = LocalDate.of(2016, 1, 1);

    @Autowired
    private DailyServiceImpl generator;

    @Autowired
    private ActivityLabelService activityLabelService;

    @Autowired
    private PersonSourceService personSourceService;

    @Test
    public void shouldGenerateWorkingDaily() {
        final LocalDateTime start = LocalDateTime.now();
        System.out.println("tests start: " + start);
        final PersonSourceDto user1 = personSourceService.findByName("user1");
        IntStream.range(338, 366).parallel().forEachOrdered(i -> {
            System.out.println("Inserting data for day: " + FIRST_DAY.plusDays(i));
            generator.createDaily(FIRST_DAY.plusDays(i), user1);
        });
        System.out.println("tests done: " + LocalDateTime.now());
        System.out.println(Duration.between(LocalDateTime.now(), start));
    }

}