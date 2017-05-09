package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import java.util.Random;

/**
 * Created by Pawel on 2017-05-07.
 */
public enum ConjuctionsSuperior {
    THEN_1(", to"),
    THEN_2(", wówczas"),
    THEN_3(", wtedy"),
    THEN_4(", w takim przypadku");

    private String value;

    ConjuctionsSuperior(String value) {
        this.value = value;
    }

    public static String getRandomConjuction() {
        Random random = new Random();
        final int index = random.nextInt(4);
        return values()[index].value;
    }
}
