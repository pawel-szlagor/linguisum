package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Immutable;
import org.mongodb.morphia.annotations.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonState;

/**
 * Created by Pawel on 2017-01-15.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Entity
public class FSnapshot {

    private BigInteger id;
    private Date date;
    private LocalTime time;
    @Singular
    private Set<PersonState> personStates;
    private FEnvironmentConditions weatherConditions;
    @Singular
    private Set<DeviceState> deviceStates;
    @Singular
    private Set<FRoomState> roomStates;
    @Singular
    private Set<FMediaUsage> mediaUsages;

}
