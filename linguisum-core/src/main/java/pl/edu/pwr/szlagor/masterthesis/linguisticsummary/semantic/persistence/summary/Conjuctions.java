package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import java.util.Random;

/**
 * Created by Pawel on 2017-05-07.
 */
public enum Conjuctions {
    AND_1("i"),
    AND_2("oraz"),
    AND_3(",a");

    private String value;

    Conjuctions(String value) {
        this.value = value;
    }

    public static String getRandomConjuction() {
        Random random = new Random();
        final int index = random.nextInt(3);
        return values()[index].value;
    }
}
