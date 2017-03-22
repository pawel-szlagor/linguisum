package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.DoubleStream.of;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot.builder;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysema.query.collections.GuavaHelpers;
import com.mysema.query.types.expr.BooleanExpression;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.MediaUsage;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.RoomState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.DeviceRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.PersonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.repository.repository.RoomRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.config.SemanticBatchConfiguration;

/**
 * Created by Pawel on 2017-03-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SemanticBatchConfiguration.class })
public class SemanticIntegratorProcessorTest {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MemGradeService memGradeService;

    @Test
    public void shouldFilterRoomStatesCorrectly() {
        // given
        final List<Snapshot> roomStates = roomRepository.findAll()
                                                        .stream()
                                                        .flatMap(r -> personRepository.findAll().stream().flatMap(
                                                                p -> of(19.0, 19.5, 20.0, 20.5, 21.0, 22.0, 23, 24.5).mapToObj(
                                                                        d -> builder().roomStates(newHashSet(new RoomState(r, p, d)))
                                                                                      .build())))
                                                        .collect(toList());

        // when
        final BooleanExpression expression = snapshot.roomStates.contains(
                new RoomState(roomRepository.findOne(1L), personRepository.findOne(1L), 20.0));
        final List<Snapshot> filtered = roomStates.stream().filter(GuavaHelpers.wrap(expression)::apply).collect(toList());
        // then
        assertThat(filtered.stream().flatMap(s -> s.getRoomStates().stream().map(RoomState::getPerson)).distinct().collect(toList()),
                Matchers.contains(personRepository.findOne(1L)));
        assertThat(filtered.stream().flatMap(s -> s.getRoomStates().stream().map(RoomState::getRoom)).distinct().collect(toList()),
                Matchers.contains(roomRepository.findOne(1L)));
        assertThat(filtered.stream().flatMap(s -> s.getRoomStates().stream().map(RoomState::getDesiredTemp)).collect(toList()),
                Matchers.contains(19.0, 19.5, 20.0, 20.5, 21.0, 22.0, 23.0, 24.5));
    }

    @Test
    public void shouldFilterMediaUsagesCorrectly() {
        // given
        final List<Snapshot> mediausages = roomRepository.findAll().stream().flatMap(r -> stream(MediaType.values()).flatMap(m -> of(19.0,
                19.5,
                20.0,
                20.5,
                21.0,
                22.0,
                23,
                24.5).mapToObj(d -> builder().mediaUsages(newHashSet(new MediaUsage(m, d, r))).build()))).collect(toList());

        // when
        final BooleanExpression expression = snapshot.mediaUsages.contains(
                new MediaUsage(MediaType.ELECTRICITY, 20.0, roomRepository.findOne(1L)));
        final List<Snapshot> filtered = mediausages.stream().filter(GuavaHelpers.wrap(expression)::apply).collect(toList());
        // then
        assertThat(filtered.stream().flatMap(s -> s.getMediaUsages().stream().map(MediaUsage::getLocation)).distinct().collect(toList()),
                Matchers.contains(roomRepository.findOne(1L)));
        assertThat(filtered.stream().flatMap(s -> s.getMediaUsages().stream().map(MediaUsage::getMediaType)).distinct().collect(toList()),
                Matchers.contains(MediaType.ELECTRICITY));
        assertThat(filtered.stream().flatMap(s -> s.getMediaUsages().stream().map(MediaUsage::getUsagePerMinute)).collect(toList()),
                Matchers.contains(19.0, 19.5, 20.0, 20.5, 21.0, 22.0, 23.0, 24.5));
    }

    @Test
    public void shouldFilterWeekdayCorrectly() {
        // given
        LocalDate localDate = LocalDate.of(2016, 1, 1);
        final List<Snapshot> days = IntStream.range(0, 10).mapToObj(d -> builder().date(localDate.plusDays(d)).build()).collect(toList());

        // when
        final BooleanExpression expression = snapshot.date.dayOfWeek().in(Calendar.SUNDAY, Calendar.SATURDAY);
        final List<Snapshot> filtered = days.stream().filter(GuavaHelpers.wrap(expression)::apply).collect(toList());
        // then
        assertThat(filtered.stream().map(Snapshot::getDate).collect(toList()),
                Matchers.contains(19.0, 19.5, 20.0, 20.5, 21.0, 22.0, 23.0, 24.5));
    }
}
