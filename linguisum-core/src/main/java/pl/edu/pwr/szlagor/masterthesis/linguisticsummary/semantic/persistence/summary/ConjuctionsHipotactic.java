package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary;

import java.util.Random;

import lombok.Getter;

/**
 * Created by Pawel on 2017-05-07.
 */
@Getter
public enum ConjuctionsHipotactic {
    IF_1("Jeżeli"),
    IF_2("Jeśli"),
    IF_3("Gdy");

    private String value;

    ConjuctionsHipotactic(String value) {
        this.value = value;
    }

    public static String getRandomConjuction() {
        Random random = new Random();
        final int index = random.nextInt(3);
        return values()[index].value;
    }
}
