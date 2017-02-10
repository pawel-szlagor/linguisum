package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.person;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.PersonSourceDto;

/**
 * Created by Pawel on 2017-01-31.
 */
public class SamplePersons {
    private static final String USER_NAME = "user1";

    public static final PersonSourceDto USER_1 = PersonSourceDto.builder().name(USER_NAME).mail("169616@student.pwr" + ".edu.pl").id(1L).build();
}
