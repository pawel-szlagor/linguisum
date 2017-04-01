package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType;

/**
 * Created by Pawel on 2017-01-16.
 */
@Document
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private MediaType mediaType;
    private double mediaUsage;

    @java.beans.ConstructorProperties({ "id", "name", "mediaType", "mediaUsage" })
    public Device(Long id, String name, MediaType mediaType, double mediaUsage) {
        this.id = id;
        this.name = name;
        this.mediaType = mediaType;
        this.mediaUsage = mediaUsage;
    }

    public Device() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public double getMediaUsage() {
        return this.mediaUsage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public void setMediaUsage(double mediaUsage) {
        this.mediaUsage = mediaUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Device device = (Device) o;

        return id != null ? id.equals(device.id) : device.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Device;
    }

    public String toString() {
		return "Device(id=" + this.getId() + ", name=" + this.getName()
                + ", mediaType=" + this.getMediaType() + ", mediaUsage=" + this.getMediaUsage() + ")";
    }
}
