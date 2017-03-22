package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Immutable;
import org.mongodb.morphia.annotations.Entity;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;

/**
 * Created by Pawel on 2017-01-15.
 */
@Immutable
@Entity
public class FSnapshot {

    private BigInteger id;
    private Date date;
    private LocalTime time;
    private Set<PersonState> personStates;
    private FEnvironmentConditions weatherConditions;
    private Set<DeviceState> deviceStates;
    private Set<FRoomState> roomStates;
    private Set<FMediaUsage> mediaUsages;

}
