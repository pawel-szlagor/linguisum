package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Embeddable;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.index.Indexed;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.WeatherEvent;

/**
 * Created by Pawel on 2017-01-15.
 */
@Entity
@Embeddable
public class EnvironmentConditions {

    @Indexed
    private double tempOut;
    private double windchill;
    @Indexed
    private double humidity;
    @Indexed
    private int pressure;
    @Indexed
    private double windSpeed;
    private double precipitation;
    private List<WeatherEvent> weatherEvents;
    @Indexed
    private double sunlightEmission;

    @java.beans.ConstructorProperties({ "tempOut", "windchill", "humidity", "pressure", "windSpeed", "precipitation", "weatherEvents",
                                        "sunlightEmission" })
    public EnvironmentConditions(double tempOut,
            double windchill,
            double humidity,
            int pressure,
            double windSpeed,
            double precipitation,
            List<WeatherEvent> weatherEvents,
            double sunlightEmission) {
        this.tempOut = tempOut;
        this.windchill = windchill;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
        this.weatherEvents = weatherEvents;
        this.sunlightEmission = sunlightEmission;
    }

    public EnvironmentConditions() {
    }

    public static EnvironmentConditionsBuilder builder() {
        return new EnvironmentConditionsBuilder();
    }

    public double getTempOut() {
        return this.tempOut;
    }

    public double getWindchill() {
        return this.windchill;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public int getPressure() {
        return this.pressure;
    }

    public double getWindSpeed() {
        return this.windSpeed;
    }

    public double getPrecipitation() {
        return this.precipitation;
    }

    public List<WeatherEvent> getWeatherEvents() {
        return this.weatherEvents;
    }

    public double getSunlightEmission() {
        return this.sunlightEmission;
    }

    public void setTempOut(double tempOut) {
        this.tempOut = tempOut;
    }

    public void setWindchill(double windchill) {
        this.windchill = windchill;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public void setWeatherEvents(List<WeatherEvent> weatherEvents) {
        this.weatherEvents = weatherEvents;
    }

    public void setSunlightEmission(double sunlightEmission) {
        this.sunlightEmission = sunlightEmission;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EnvironmentConditions))
            return false;
        final EnvironmentConditions other = (EnvironmentConditions) o;
        if (!other.canEqual((Object) this))
            return false;
        if (Double.compare(this.getTempOut(), other.getTempOut()) != 0)
            return false;
        if (Double.compare(this.getWindchill(), other.getWindchill()) != 0)
            return false;
        if (Double.compare(this.getHumidity(), other.getHumidity()) != 0)
            return false;
        if (this.getPressure() != other.getPressure())
            return false;
        if (Double.compare(this.getWindSpeed(), other.getWindSpeed()) != 0)
            return false;
        if (Double.compare(this.getPrecipitation(), other.getPrecipitation()) != 0)
            return false;
        final Object this$weatherEvents = this.getWeatherEvents();
        final Object other$weatherEvents = other.getWeatherEvents();
        if (this$weatherEvents == null ? other$weatherEvents != null : !this$weatherEvents.equals(other$weatherEvents))
            return false;
        if (Double.compare(this.getSunlightEmission(), other.getSunlightEmission()) != 0)
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $tempOut = Double.doubleToLongBits(this.getTempOut());
        result = result * PRIME + (int) ($tempOut >>> 32 ^ $tempOut);
        final long $windchill = Double.doubleToLongBits(this.getWindchill());
        result = result * PRIME + (int) ($windchill >>> 32 ^ $windchill);
        final long $humidity = Double.doubleToLongBits(this.getHumidity());
        result = result * PRIME + (int) ($humidity >>> 32 ^ $humidity);
        result = result * PRIME + this.getPressure();
        final long $windSpeed = Double.doubleToLongBits(this.getWindSpeed());
        result = result * PRIME + (int) ($windSpeed >>> 32 ^ $windSpeed);
        final long $precipitation = Double.doubleToLongBits(this.getPrecipitation());
        result = result * PRIME + (int) ($precipitation >>> 32 ^ $precipitation);
        final Object $weatherEvents = this.getWeatherEvents();
        result = result * PRIME + ($weatherEvents == null ? 43 : $weatherEvents.hashCode());
        final long $sunlightEmission = Double.doubleToLongBits(this.getSunlightEmission());
        result = result * PRIME + (int) ($sunlightEmission >>> 32 ^ $sunlightEmission);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof EnvironmentConditions;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditions(tempOut=" + this.getTempOut()
                + ", windchill=" + this.getWindchill() + ", humidity=" + this.getHumidity() + ", pressure=" + this.getPressure()
                + ", windSpeed=" + this.getWindSpeed() + ", precipitation=" + this.getPrecipitation() + ", weatherEvents="
                + this.getWeatherEvents() + ", sunlightEmission=" + this.getSunlightEmission() + ")";
    }

    public static class EnvironmentConditionsBuilder {
        private double tempOut;
        private double windchill;
        private double humidity;
        private int pressure;
        private double windSpeed;
        private double precipitation;
        private ArrayList<WeatherEvent> weatherEvents;
        private double sunlightEmission;

        EnvironmentConditionsBuilder() {
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder tempOut(double tempOut) {
            this.tempOut = tempOut;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder windchill(double windchill) {
            this.windchill = windchill;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder humidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder windSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder precipitation(double precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder weatherEvent(WeatherEvent weatherEvent) {
            if (this.weatherEvents == null)
                this.weatherEvents = new ArrayList<WeatherEvent>();
            this.weatherEvents.add(weatherEvent);
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder weatherEvents(Collection<? extends WeatherEvent> weatherEvents) {
            if (this.weatherEvents == null)
                this.weatherEvents = new ArrayList<WeatherEvent>();
            this.weatherEvents.addAll(weatherEvents);
            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder clearWeatherEvents() {
            if (this.weatherEvents != null)
                this.weatherEvents.clear();

            return this;
        }

        public EnvironmentConditions.EnvironmentConditionsBuilder sunlightEmission(double sunlightEmission) {
            this.sunlightEmission = sunlightEmission;
            return this;
        }

        public EnvironmentConditions build() {
            List<WeatherEvent> weatherEvents;
            switch (this.weatherEvents == null ? 0 : this.weatherEvents.size()) {
                case 0:
                    weatherEvents = java.util.Collections.emptyList();
                    break;
                case 1:
                    weatherEvents = java.util.Collections.singletonList(this.weatherEvents.get(0));
                    break;
                default:
                    weatherEvents = java.util.Collections.unmodifiableList(new ArrayList<WeatherEvent>(this.weatherEvents));
            }

            return new EnvironmentConditions(tempOut, windchill, humidity, pressure, windSpeed, precipitation, weatherEvents,
                                             sunlightEmission);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.EnvironmentConditions.EnvironmentConditionsBuilder(tempOut="
                    + this.tempOut + ", windchill=" + this.windchill + ", humidity=" + this.humidity + ", pressure=" + this.pressure
                    + ", windSpeed=" + this.windSpeed + ", precipitation=" + this.precipitation + ", weatherEvents=" + this.weatherEvents
                    + ", sunlightEmission=" + this.sunlightEmission + ")";
        }
    }
}
