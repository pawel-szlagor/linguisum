package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy;

import static org.apache.commons.collections.CollectionUtils.intersection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mongodb.morphia.annotations.Entity;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.TrapezoidalMemGrade;

/**
 * Created by Pawel on 2017-01-15.
 */
@Entity
public class FEnvironmentConditions {

    private Set<TrapezoidalMemGrade> fTempOut;
    private Map<TrapezoidalMemGrade, Double> fTempOutProb;
    private Set<TrapezoidalMemGrade> fWindchill;
    private Map<TrapezoidalMemGrade, Double> fWindchillProb;
    private Set<TrapezoidalMemGrade> fHumidity;
    private Map<TrapezoidalMemGrade, Double> fHumidityProb;
    private Set<TrapezoidalMemGrade> fPressure;
    private Map<TrapezoidalMemGrade, Double> fPressureProb;
    private Set<TrapezoidalMemGrade> fWindSpeed;
    private Map<TrapezoidalMemGrade, Double> fWindSpeedProb;
    private Set<TrapezoidalMemGrade> fPrecipitation;
    private Map<TrapezoidalMemGrade, Double> fPrecipitationProb;
    private List<WeatherEvent> weatherEvents;
    private Set<TrapezoidalMemGrade> fSunlightEmission;
    private Map<TrapezoidalMemGrade, Double> fSunlightEmissionProb;

    @java.beans.ConstructorProperties({ "fTempOut", "fTempOutProb", "fWindchill", "fWindchillProb", "fHumidity", "fHumidityProb",
                                        "fPressure", "fPressureProb", "fWindSpeed", "fWindSpeedProb", "fPrecipitation",
                                        "fPrecipitationProb", "weatherEvents", "fSunlightEmission", "fSunlightEmissionProb" })
    public FEnvironmentConditions(Set<TrapezoidalMemGrade> fTempOut,
            Map<TrapezoidalMemGrade, Double> fTempOutProb,
            Set<TrapezoidalMemGrade> fWindchill,
            Map<TrapezoidalMemGrade, Double> fWindchillProb,
            Set<TrapezoidalMemGrade> fHumidity,
            Map<TrapezoidalMemGrade, Double> fHumidityProb,
            Set<TrapezoidalMemGrade> fPressure,
            Map<TrapezoidalMemGrade, Double> fPressureProb,
            Set<TrapezoidalMemGrade> fWindSpeed,
            Map<TrapezoidalMemGrade, Double> fWindSpeedProb,
            Set<TrapezoidalMemGrade> fPrecipitation,
            Map<TrapezoidalMemGrade, Double> fPrecipitationProb,
            List<WeatherEvent> weatherEvents,
            Set<TrapezoidalMemGrade> fSunlightEmission,
            Map<TrapezoidalMemGrade, Double> fSunlightEmissionProb) {
        this.fTempOut = fTempOut;
        this.fTempOutProb = fTempOutProb;
        this.fWindchill = fWindchill;
        this.fWindchillProb = fWindchillProb;
        this.fHumidity = fHumidity;
        this.fHumidityProb = fHumidityProb;
        this.fPressure = fPressure;
        this.fPressureProb = fPressureProb;
        this.fWindSpeed = fWindSpeed;
        this.fWindSpeedProb = fWindSpeedProb;
        this.fPrecipitation = fPrecipitation;
        this.fPrecipitationProb = fPrecipitationProb;
        this.weatherEvents = weatherEvents;
        this.fSunlightEmission = fSunlightEmission;
        this.fSunlightEmissionProb = fSunlightEmissionProb;
    }

    public FEnvironmentConditions() {
    }

    public static FEnvironmentConditionsBuilder builder() {
        return new FEnvironmentConditionsBuilder();
    }

    public Set<TrapezoidalMemGrade> getFTempOut() {
        return this.fTempOut;
    }

    public Map<TrapezoidalMemGrade, Double> getFTempOutProb() {
        return this.fTempOutProb;
    }

    public Set<TrapezoidalMemGrade> getFWindchill() {
        return this.fWindchill;
    }

    public Map<TrapezoidalMemGrade, Double> getFWindchillProb() {
        return this.fWindchillProb;
    }

    public Set<TrapezoidalMemGrade> getFHumidity() {
        return this.fHumidity;
    }

    public Map<TrapezoidalMemGrade, Double> getFHumidityProb() {
        return this.fHumidityProb;
    }

    public Set<TrapezoidalMemGrade> getFPressure() {
        return this.fPressure;
    }

    public Map<TrapezoidalMemGrade, Double> getFPressureProb() {
        return this.fPressureProb;
    }

    public Set<TrapezoidalMemGrade> getFWindSpeed() {
        return this.fWindSpeed;
    }

    public Map<TrapezoidalMemGrade, Double> getFWindSpeedProb() {
        return this.fWindSpeedProb;
    }

    public Set<TrapezoidalMemGrade> getFPrecipitation() {
        return this.fPrecipitation;
    }

    public Map<TrapezoidalMemGrade, Double> getFPrecipitationProb() {
        return this.fPrecipitationProb;
    }

    public List<WeatherEvent> getWeatherEvents() {
        return this.weatherEvents;
    }

    public Set<TrapezoidalMemGrade> getFSunlightEmission() {
        return this.fSunlightEmission;
    }

    public Map<TrapezoidalMemGrade, Double> getFSunlightEmissionProb() {
        return this.fSunlightEmissionProb;
    }

    public void setFTempOut(Set<TrapezoidalMemGrade> fTempOut) {
        this.fTempOut = fTempOut;
    }

    public void setFTempOutProb(Map<TrapezoidalMemGrade, Double> fTempOutProb) {
        this.fTempOutProb = fTempOutProb;
    }

    public void setFWindchill(Set<TrapezoidalMemGrade> fWindchill) {
        this.fWindchill = fWindchill;
    }

    public void setFWindchillProb(Map<TrapezoidalMemGrade, Double> fWindchillProb) {
        this.fWindchillProb = fWindchillProb;
    }

    public void setFHumidity(Set<TrapezoidalMemGrade> fHumidity) {
        this.fHumidity = fHumidity;
    }

    public void setFHumidityProb(Map<TrapezoidalMemGrade, Double> fHumidityProb) {
        this.fHumidityProb = fHumidityProb;
    }

    public void setFPressure(Set<TrapezoidalMemGrade> fPressure) {
        this.fPressure = fPressure;
    }

    public void setFPressureProb(Map<TrapezoidalMemGrade, Double> fPressureProb) {
        this.fPressureProb = fPressureProb;
    }

    public void setFWindSpeed(Set<TrapezoidalMemGrade> fWindSpeed) {
        this.fWindSpeed = fWindSpeed;
    }

    public void setFWindSpeedProb(Map<TrapezoidalMemGrade, Double> fWindSpeedProb) {
        this.fWindSpeedProb = fWindSpeedProb;
    }

    public void setFPrecipitation(Set<TrapezoidalMemGrade> fPrecipitation) {
        this.fPrecipitation = fPrecipitation;
    }

    public void setFPrecipitationProb(Map<TrapezoidalMemGrade, Double> fPrecipitationProb) {
        this.fPrecipitationProb = fPrecipitationProb;
    }

    public void setWeatherEvents(List<WeatherEvent> weatherEvents) {
        this.weatherEvents = weatherEvents;
    }

    public void setFSunlightEmission(Set<TrapezoidalMemGrade> fSunlightEmission) {
        this.fSunlightEmission = fSunlightEmission;
    }

    public void setFSunlightEmissionProb(Map<TrapezoidalMemGrade, Double> fSunlightEmissionProb) {
        this.fSunlightEmissionProb = fSunlightEmissionProb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FEnvironmentConditions that = (FEnvironmentConditions) o;

        if (fTempOut != null ? !intersection(fTempOut, that.fTempOut).isEmpty() : that.fTempOut != null)
            return false;
        if (fWindchill != null ? !intersection(fWindchill, that.fWindchill).isEmpty() : that.fWindchill != null)
            return false;
        if (fHumidity != null ? !intersection(fHumidity, that.fHumidity).isEmpty() : that.fHumidity != null)
            return false;
        if (fPressure != null ? !intersection(fPressure, that.fPressure).isEmpty() : that.fPressure != null)
            return false;
        if (fWindSpeed != null ? !intersection(fWindSpeed, that.fWindSpeed).isEmpty() : that.fWindSpeed != null)
            return false;
        if (fPrecipitation != null ? !intersection(fPrecipitation, that.fPrecipitation).isEmpty() : that.fPrecipitation != null)
            return false;
        if (weatherEvents != null ? !weatherEvents.equals(that.weatherEvents) : that.weatherEvents != null)
            return false;
        return fSunlightEmission != null ? intersection(fSunlightEmission, that.fSunlightEmission).isEmpty()
                : that.fSunlightEmission == null;
    }

    @Override
    public int hashCode() {
        int result = fTempOut != null ? fTempOut.hashCode() : 0;
        result = 31 * result + (fWindchill != null ? fWindchill.hashCode() : 0);
        result = 31 * result + (fHumidity != null ? fHumidity.hashCode() : 0);
        result = 31 * result + (fPressure != null ? fPressure.hashCode() : 0);
        result = 31 * result + (fWindSpeed != null ? fWindSpeed.hashCode() : 0);
        result = 31 * result + (fPrecipitation != null ? fPrecipitation.hashCode() : 0);
        result = 31 * result + (weatherEvents != null ? weatherEvents.hashCode() : 0);
        result = 31 * result + (fSunlightEmission != null ? fSunlightEmission.hashCode() : 0);
        return result;
    }

    public static class FEnvironmentConditionsBuilder {
        private Set<TrapezoidalMemGrade> fTempOut;
        private Map<TrapezoidalMemGrade, Double> fTempOutProb;
        private Set<TrapezoidalMemGrade> fWindchill;
        private Map<TrapezoidalMemGrade, Double> fWindchillProb;
        private Set<TrapezoidalMemGrade> fHumidity;
        private Map<TrapezoidalMemGrade, Double> fHumidityProb;
        private Set<TrapezoidalMemGrade> fPressure;
        private Map<TrapezoidalMemGrade, Double> fPressureProb;
        private Set<TrapezoidalMemGrade> fWindSpeed;
        private Map<TrapezoidalMemGrade, Double> fWindSpeedProb;
        private Set<TrapezoidalMemGrade> fPrecipitation;
        private Map<TrapezoidalMemGrade, Double> fPrecipitationProb;
        private List<WeatherEvent> weatherEvents;
        private Set<TrapezoidalMemGrade> fSunlightEmission;
        private Map<TrapezoidalMemGrade, Double> fSunlightEmissionProb;

        FEnvironmentConditionsBuilder() {
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fTempOut(Set<TrapezoidalMemGrade> fTempOut) {
            this.fTempOut = fTempOut;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fTempOutProb(Map<TrapezoidalMemGrade, Double> fTempOutProb) {
            this.fTempOutProb = fTempOutProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fWindchill(Set<TrapezoidalMemGrade> fWindchill) {
            this.fWindchill = fWindchill;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fWindchillProb(Map<TrapezoidalMemGrade, Double> fWindchillProb) {
            this.fWindchillProb = fWindchillProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fHumidity(Set<TrapezoidalMemGrade> fHumidity) {
            this.fHumidity = fHumidity;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fHumidityProb(Map<TrapezoidalMemGrade, Double> fHumidityProb) {
            this.fHumidityProb = fHumidityProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fPressure(Set<TrapezoidalMemGrade> fPressure) {
            this.fPressure = fPressure;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fPressureProb(Map<TrapezoidalMemGrade, Double> fPressureProb) {
            this.fPressureProb = fPressureProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fWindSpeed(Set<TrapezoidalMemGrade> fWindSpeed) {
            this.fWindSpeed = fWindSpeed;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fWindSpeedProb(Map<TrapezoidalMemGrade, Double> fWindSpeedProb) {
            this.fWindSpeedProb = fWindSpeedProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fPrecipitation(Set<TrapezoidalMemGrade> fPrecipitation) {
            this.fPrecipitation = fPrecipitation;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fPrecipitationProb(
                Map<TrapezoidalMemGrade, Double> fPrecipitationProb) {
            this.fPrecipitationProb = fPrecipitationProb;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder weatherEvents(List<WeatherEvent> weatherEvents) {
            this.weatherEvents = weatherEvents;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fSunlightEmission(Set<TrapezoidalMemGrade> fSunlightEmission) {
            this.fSunlightEmission = fSunlightEmission;
            return this;
        }

        public FEnvironmentConditions.FEnvironmentConditionsBuilder fSunlightEmissionProb(
                Map<TrapezoidalMemGrade, Double> fSunlightEmissionProb) {
            this.fSunlightEmissionProb = fSunlightEmissionProb;
            return this;
        }

        public FEnvironmentConditions build() {
            return new FEnvironmentConditions(fTempOut, fTempOutProb, fWindchill, fWindchillProb, fHumidity, fHumidityProb, fPressure,
                                              fPressureProb, fWindSpeed, fWindSpeedProb, fPrecipitation, fPrecipitationProb, weatherEvents,
                                              fSunlightEmission, fSunlightEmissionProb);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.fuzzy.FEnvironmentConditions.FEnvironmentConditionsBuilder(fTempOut="
                    + this.fTempOut + ", fTempOutProb=" + this.fTempOutProb + ", fWindchill=" + this.fWindchill + ", fWindchillProb="
                    + this.fWindchillProb + ", fHumidity=" + this.fHumidity + ", fHumidityProb=" + this.fHumidityProb + ", fPressure="
                    + this.fPressure + ", fPressureProb=" + this.fPressureProb + ", fWindSpeed=" + this.fWindSpeed + ", fWindSpeedProb="
                    + this.fWindSpeedProb + ", fPrecipitation=" + this.fPrecipitation + ", fPrecipitationProb=" + this.fPrecipitationProb
                    + ", weatherEvents=" + this.weatherEvents + ", fSunlightEmission=" + this.fSunlightEmission + ", fSunlightEmissionProb="
                    + this.fSunlightEmissionProb + ")";
        }
    }
}
