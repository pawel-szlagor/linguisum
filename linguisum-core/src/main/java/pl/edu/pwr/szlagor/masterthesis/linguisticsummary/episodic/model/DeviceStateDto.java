package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

/**
 * Created by Pawel on 2017-01-16.
 */
public class DeviceStateDto {

    private DeviceDto device;
    private boolean isOn;

    @java.beans.ConstructorProperties({ "device", "isOn" })
    public DeviceStateDto(DeviceDto device, boolean isOn) {
        this.device = device;
        this.isOn = isOn;
    }

    public DeviceStateDto() {
    }

    public DeviceDto getDevice() {
        return this.device;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setDevice(DeviceDto device) {
        this.device = device;
    }

    public void setOn(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DeviceStateDto))
            return false;
        final DeviceStateDto other = (DeviceStateDto) o;
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
        return other instanceof DeviceStateDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.DeviceStateDto(device=" + this.getDevice() + ", isOn="
                + this.isOn() + ")";
    }
}
