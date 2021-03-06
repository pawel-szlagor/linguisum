package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.processor;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.DoubleStream.of;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.QSnapshot.snapshot;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Snapshot.builder;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
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
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.QFSnapshot;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.levels.MemGradeService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.integrator.integrator.job.config.SemanticBatchConfiguration;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.HolonRepository;

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
    @Autowired
    private HolonRepository holonRepository;

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
        final Holon holon = Holon.builder().cardinality(new AtomicLong(123L)).predicate(expression).build();
        holonRepository.save(holon);
        final Holon one = holonRepository.findOne(holon.getId());
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
        final List<FSnapshot> days = IntStream.range(0, 10)
                                              .mapToObj(d -> FSnapshot.builder().date(Date.valueOf(localDate.plusDays(d))).build())
                                              .collect(toList());

        // when
        final BooleanExpression expression = QFSnapshot.fSnapshot.date.dayOfWeek().in(Calendar.SUNDAY, Calendar.SATURDAY);
        final List<FSnapshot> filtered = days.stream().filter(GuavaHelpers.wrap(expression)::apply).collect(toList());
        // then
        assertThat(filtered.stream().map(FSnapshot::getDate).collect(toList()),
                Matchers.contains(19.0, 19.5, 20.0, 20.5, 21.0, 22.0, 23.0, 24.5));
    }

    @Test
    public void shouldFilterHourlyCorrectly() {
        // given
        LocalTime time = LocalTime.of(12, 0, 0);
        final List<FSnapshot> days = IntStream.iterate(0, i -> i + 15)
                                              .limit(8)
                                              .mapToObj(d -> FSnapshot.builder().time(time.plusMinutes(d)).build())
                                              .collect(toList());

        // when
        final BooleanExpression expression = QFSnapshot.fSnapshot.time.between(time.plusHours(1L), time.plusHours(2));
        final List<FSnapshot> filtered = days.stream().filter(GuavaHelpers.wrap(expression)::apply).collect(toList());
        // then
        assertThat(filtered.stream().map(FSnapshot::getTime).map(LocalTime::getHour).collect(toSet()), Matchers.contains(13));
    }
}
