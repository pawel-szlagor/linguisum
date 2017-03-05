package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Pawel on 2017-01-31.
 */
@EqualsAndHashCode
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySchemaSourceDto {
    private double averageElapsedTime;
    private String name;
    private Map<RoomSourceDto, Double> locationProbabilities = new HashMap<>();
    private Map<RoomSourceDto, Double> preferredTempInLocations = new HashMap<>();
    private PersonSourceDto user;
    private Map<DeviceSourceDto, Double> timeOfDeviceUsages = new HashMap<>();
    private List<MediaUsageSourceDto> mediaUsageSourceDtos = new ArrayList<>();

    public static ActivityBuilder builder() {
        return new ActivityBuilder();
    }

    public static class ActivityBuilder {
        private double averageElapsedTime;
        private String name;
        private Map<RoomSourceDto, Double> locationProbabilities = new HashMap<>();
        private Map<RoomSourceDto, Double> preferredTempInLocations = new HashMap<>();
        private PersonSourceDto user;
        private Map<DeviceSourceDto, Double> timeOfDeviceUsages = new HashMap<>();
        private List<MediaUsageSourceDto> mediaUsageSourceDtos = new ArrayList<>();

        ActivityBuilder() {
        }

        public ActivitySchemaSourceDto.ActivityBuilder averageElapsedTime(double averageElapsedTime) {
            this.averageElapsedTime = averageElapsedTime;
            return this;
        }

        public ActivitySchemaSourceDto.ActivityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ActivitySchemaSourceDto.ActivityBuilder locationProbability(RoomSourceDto locationProbabilityKey, Double locationProbabilityValue) {
            locationProbabilities.put(locationProbabilityKey, locationProbabilityValue);
            return this;
        }

        public ActivitySchemaSourceDto.ActivityBuilder preferredTempInLocation(RoomSourceDto preferredTempInLocationKey, Double preferredTempInLocationValue) {
            preferredTempInLocations.put(preferredTempInLocationKey, preferredTempInLocationValue);
            return this;
        }

        public ActivitySchemaSourceDto.ActivityBuilder user(PersonSourceDto user) {
            this.user = user;
            return this;
        }

        public ActivitySchemaSourceDto.ActivityBuilder timeOfDeviceUsage(DeviceSourceDto timeOfDeviceUsageKey, Double timeOfDeviceUsageValue) {
            timeOfDeviceUsages.put(timeOfDeviceUsageKey, timeOfDeviceUsageValue);
            return this;
        }


        public ActivitySchemaSourceDto.ActivityBuilder mediaUsageSourceDto(MediaUsageSourceDto mediaUsageSourceDto) {
            if (this.mediaUsageSourceDtos == null) this.mediaUsageSourceDtos = new ArrayList<MediaUsageSourceDto>();
            this.mediaUsageSourceDtos.add(mediaUsageSourceDto);
            return this;
        }

        public ActivitySchemaSourceDto build() {

            return new ActivitySchemaSourceDto(averageElapsedTime, name, locationProbabilities, preferredTempInLocations, user, timeOfDeviceUsages, mediaUsageSourceDtos);
        }
    }
}
