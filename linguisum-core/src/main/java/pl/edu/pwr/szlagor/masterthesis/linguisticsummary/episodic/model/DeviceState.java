package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Created by Pawel on 2017-01-16.
 */
@Embeddable
public class DeviceState {

    @Indexed
    private Device device;
    private boolean isOn;

    @java.beans.ConstructorProperties({ "device", "isOn" })
    public DeviceState(Device device, boolean isOn) {
        this.device = device;
        this.isOn = isOn;
    }

    public DeviceState() {
    }

    public static DeviceStateBuilder builder() {
        return new DeviceStateBuilder();
    }

    public Device getDevice() {
        return this.device;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeviceState))
            return false;
        final DeviceState other = (DeviceState) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$device = this.getDevice();
        final Object other$device = other.getDevice();
        if (this$device == null ? other$device != null : !this$device.equals(other$device))
            return false;
        if (this.isOn() != other.isOn())
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $device = this.getDevice();
        result = result * PRIME + ($device == null ? 43 : $device.hashCode());
        result = result * PRIME + (this.isOn() ? 79 : 97);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof DeviceState;
    }

    public String toString() {
		return "DeviceState(device=" + this.getDevice() + ", isOn="
                + this.isOn() + ")";
    }

    public static class DeviceStateBuilder {
        private Device device;
        private boolean isOn;

        DeviceStateBuilder() {
        }

        public DeviceState.DeviceStateBuilder device(Device device) {
            this.device = device;
            return this;
        }

        public DeviceState.DeviceStateBuilder isOn(boolean isOn) {
            this.isOn = isOn;
            return this;
        }

        public DeviceState build() {
            return new DeviceState(device, isOn);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceState.DeviceStateBuilder(device=" + this.device
                    + ", isOn=" + this.isOn + ")";
        }
    }
}
