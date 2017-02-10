package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.COLD_WATER;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.ELECTRICITY;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.DeviceSourceDto;

/**
 * Created by Pawel on 2017-01-31.
 */
public class SampleDevices {
    public static final DeviceSourceDto OVEN = DeviceSourceDto.builder().id(1L).mediaType(ELECTRICITY).mediaUsage(54.5d).name("oven").build();
    public static final DeviceSourceDto TV_SET = DeviceSourceDto.builder().id(2L).mediaType(ELECTRICITY).mediaUsage(30d).name("tv set").build();
    public static final DeviceSourceDto CONSOLE = DeviceSourceDto.builder().id(3L).mediaType(ELECTRICITY).mediaUsage(10d).name("console").build();
    public static final DeviceSourceDto WASH_MACHINE = DeviceSourceDto.builder().id(4L).mediaType(COLD_WATER).mediaUsage(5.5d).name("wash machine").build();
    public static final DeviceSourceDto PC = DeviceSourceDto.builder().id(5L).mediaType(ELECTRICITY).mediaUsage(23).name("personal computer").build();
    public static final DeviceSourceDto MICROWAVE = DeviceSourceDto.builder().id(6L).mediaType(ELECTRICITY).mediaUsage(85.5d).name("microwave").build();
    public static final DeviceSourceDto FRIDGE = DeviceSourceDto.builder().id(7L).mediaType(ELECTRICITY).mediaUsage(12.5d).name("fridge-freezer").build();
    public static final DeviceSourceDto EL_KETTLE = DeviceSourceDto.builder().id(8L).mediaType(ELECTRICITY).mediaUsage(75.5d).name("electric kettle").build();
    public static final DeviceSourceDto SLEEPINGROOM_LIGHT = DeviceSourceDto.builder().id(9L).mediaType(ELECTRICITY).mediaUsage(5.0d).name("light in sleeping room").build();
    public static final DeviceSourceDto LAPTOP = DeviceSourceDto.builder().id(10L).mediaType(ELECTRICITY).mediaUsage(8.0d).name("laptop").build();
}
