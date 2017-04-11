package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.DEVICE_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.HUMIDITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.MEDIA_USAGE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PERSON_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION_TYPE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRESSURE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.ROOM_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.SUNLIGHT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.TEMP_OUT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.WIND_SPEED;

import com.google.common.collect.Sets;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.SummaryProfile;

/**
 * Created by Pawel on 2017-03-12.
 */
public class SummaryProfiles {
    public static final SummaryProfile ACTIVITY_TO_DES_TEMP = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(PERSON_STATE,
                                                                                    DEVICE_STATE,
                                                                                    MEDIA_USAGE))
                                                                            .resultFactor(ROOM_STATE)
                                                                            .build();
    public static final SummaryProfile ACTIVITY_TO_DES_TEMP2 = SummaryProfile.builder()
                                                                             .user(Person.builder().id(1L).build())
                                                                             .summaryfactors(Sets.newHashSet(PERSON_STATE, DEVICE_STATE))
                                                                             .resultFactor(ROOM_STATE)
                                                                             .build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP = SummaryProfile.builder()
                                                                           .user(Person.builder().id(1L).build())
                                                                           .summaryfactors(Sets.newHashSet(TEMP_OUT, SUNLIGHT, PRECIPITATION

                                                                           )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP2 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(SUNLIGHT,
                                                                                    PRECIPITATION_TYPE,
                                                                                    PRECIPITATION,
                                                                                    WIND_SPEED

                                                                            )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP3 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(
                                                                                    Sets.newHashSet(TEMP_OUT, PERSON_STATE, PRECIPITATION

                                                                                    )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP4 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(
                                                                                    Sets.newHashSet(PERSON_STATE, TEMP_OUT, WIND_SPEED

                                                                                    )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP5 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(PERSON_STATE, TEMP_OUT

                                                                            )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP6 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(TEMP_OUT, DEVICE_STATE

                                                                            )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP7 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(
                                                                                    Sets.newHashSet(TEMP_OUT, PRESSURE, SUNLIGHT, HUMIDITY

                                                                                    )).resultFactor(ROOM_STATE).build();

    public static final SummaryProfile WEATHER_TO_DES_TEMP8 = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(TEMP_OUT,
                                                                                    PERSON_STATE,
                                                                                    DEVICE_STATE,
                                                                                    SUNLIGHT

                                                                            )).resultFactor(ROOM_STATE).build();
}
