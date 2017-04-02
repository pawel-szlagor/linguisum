package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.DEVICE_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.HUMIDITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.MEDIA_USAGE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PERSON_STATE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes.PRECIPITATION_TYPE;
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
    public static final SummaryProfile WEATHER_TO_DES_TEMP = SummaryProfile.builder()
                                                                           .user(Person.builder().id(1L).build())
                                                                           .summaryfactors(Sets.newHashSet(TEMP_OUT,
                                                                                   HUMIDITY,
                                                                                   PRECIPITATION,
                                                                                   PRECIPITATION_TYPE,
                                                                                   WIND_SPEED,
                                                                                   SUNLIGHT))
                                                                           .resultFactor(ROOM_STATE)
                                                                           .build();
    public static final SummaryProfile ACTIVITY_TO_DES_TEMP = SummaryProfile.builder()
                                                                            .user(Person.builder().id(1L).build())
                                                                            .summaryfactors(Sets.newHashSet(PERSON_STATE,
                                                                                    DEVICE_STATE,
                                                                                    MEDIA_USAGE))
                                                                            .resultFactor(ROOM_STATE)
                                                                            .build();
}
