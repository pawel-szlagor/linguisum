package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.activity;

import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.COLD_WATER;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.ELECTRICITY;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.enums.MediaType.HOT_WATER;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.CONSOLE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.EL_KETTLE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.MICROWAVE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.OVEN;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.PC;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.SLEEPINGROOM_LIGHT;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.TV_SET;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.device.SampleDevices.WASH_MACHINE;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.BATHROOM;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.DINING_ROOM;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.HALL;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.KITCHEN;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.LIVING_ROOM;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.SLEEPING_ROOM_1;
import static pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms.SLEEPING_ROOM_2;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.ActivitySchemaSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.model.MediaUsageSourceDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.service.room.SampleRooms;

/**
 * Created by Pawel on 2017-01-31.
 */
public class SampleActivities {

    public static final ActivitySchemaSourceDto SLEEPING = ActivitySchemaSourceDto.builder().averageElapsedTime(8 * 60).name("sleeping").locationProbability(SLEEPING_ROOM_1, 0.90d).locationProbability(SampleRooms.TOILETTE, 0.05d).locationProbability(BATHROOM, 0.05d).preferredTempInLocation(SLEEPING_ROOM_1, 18d).preferredTempInLocation(SampleRooms.TOILETTE, 20.5d).preferredTempInLocation(BATHROOM, 20.5d).build();
    public static final ActivitySchemaSourceDto WAKING_UP = ActivitySchemaSourceDto.builder().averageElapsedTime(10d).name("wakingUp").locationProbability(SLEEPING_ROOM_1, 0.5).locationProbability(BATHROOM, 0.2).locationProbability(SampleRooms.TOILETTE, 0.1).locationProbability(KITCHEN, 0.1).locationProbability(HALL, 0.05).locationProbability(LIVING_ROOM, 0.05).timeOfDeviceUsage(SLEEPINGROOM_LIGHT, 0.9).timeOfDeviceUsage(EL_KETTLE, 0.1).preferredTempInLocation(SLEEPING_ROOM_1, 21d).preferredTempInLocation(SampleRooms.TOILETTE, 21d).preferredTempInLocation(BATHROOM, 22d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(4d).location(SLEEPING_ROOM_1).build()).build();
    public static final ActivitySchemaSourceDto MEAL = ActivitySchemaSourceDto.builder().averageElapsedTime(20d).name("meal").locationProbability(KITCHEN, 0.5).locationProbability(DINING_ROOM, 0.5).timeOfDeviceUsage(OVEN, 0.1).timeOfDeviceUsage(EL_KETTLE, 0.1).timeOfDeviceUsage(MICROWAVE, 0.1).preferredTempInLocation(KITCHEN, 21d).preferredTempInLocation(DINING_ROOM, 21d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(20d).location(KITCHEN).build()).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(5d).location(DINING_ROOM).build()).build();
    public static final ActivitySchemaSourceDto COOKING = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("cooking").locationProbability(KITCHEN, 0.9).locationProbability(DINING_ROOM, 0.1).timeOfDeviceUsage(OVEN, 0.7).timeOfDeviceUsage(MICROWAVE, 0.1).preferredTempInLocation(KITCHEN, 19d).preferredTempInLocation(DINING_ROOM, 20d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(40).location(KITCHEN).build()).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(COLD_WATER).usagePerMinute(5d).location(KITCHEN).build()).build();
    public static final ActivitySchemaSourceDto BATH = ActivitySchemaSourceDto.builder().averageElapsedTime(30d).name("bathing").locationProbability(BATHROOM, 1.0).preferredTempInLocation(BATHROOM, 22d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(COLD_WATER).usagePerMinute(10d).location(BATHROOM).build()).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(HOT_WATER).usagePerMinute(10d).location(BATHROOM).build()).build();
    public static final ActivitySchemaSourceDto TOILETTE = ActivitySchemaSourceDto.builder().averageElapsedTime(5d).name("toilette").locationProbability(BATHROOM, 1.0).preferredTempInLocation(BATHROOM, 22d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(COLD_WATER).usagePerMinute(5d).location(BATHROOM).build()).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(HOT_WATER).usagePerMinute(5d).location(BATHROOM).build()).build();
    public static final ActivitySchemaSourceDto DO_WASHING = ActivitySchemaSourceDto.builder().averageElapsedTime(150d).name("do washing").locationProbability(BATHROOM, 0.05).preferredTempInLocation(BATHROOM, 21d).timeOfDeviceUsage(WASH_MACHINE, 0.9).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(COLD_WATER).usagePerMinute(7d).location(BATHROOM).build()).build();
    public static final ActivitySchemaSourceDto DRY_LAUNDRY = ActivitySchemaSourceDto.builder().averageElapsedTime(240d).name("drying laundry").locationProbability(BATHROOM, 0.05).preferredTempInLocation(BATHROOM, 25d).build();
    public static final ActivitySchemaSourceDto WORK_OUT = ActivitySchemaSourceDto.builder().averageElapsedTime(45d).name("working out").locationProbability(LIVING_ROOM, 0.6).locationProbability(SLEEPING_ROOM_1, 0.4).preferredTempInLocation(LIVING_ROOM, 18d).preferredTempInLocation(SLEEPING_ROOM_1, 18d).build();
    public static final ActivitySchemaSourceDto WATCHING_TV = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("watching TV").locationProbability(LIVING_ROOM, 0.9).locationProbability(KITCHEN, 0.05).locationProbability(SampleRooms.TOILETTE, 0.05).preferredTempInLocation(LIVING_ROOM, 22d).timeOfDeviceUsage(TV_SET, 1.0).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(3d).location(LIVING_ROOM).build()).build();
    public static final ActivitySchemaSourceDto PLAYING_CONSOLE = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("playing console TV").locationProbability(LIVING_ROOM, 0.95).locationProbability(SampleRooms.TOILETTE, 0.05).preferredTempInLocation(LIVING_ROOM, 21d).timeOfDeviceUsage(CONSOLE, 1.0).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(3d).location(LIVING_ROOM).build()).build();
    public static final ActivitySchemaSourceDto WORK_ON_DESK = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("working on desk").locationProbability(SLEEPING_ROOM_1, 0.5).locationProbability(SLEEPING_ROOM_2, 0.05).preferredTempInLocation(SLEEPING_ROOM_1, 22d).preferredTempInLocation(SLEEPING_ROOM_2, 22d).timeOfDeviceUsage(PC, 1.0).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(5d).location(SLEEPING_ROOM_1).build()).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(5d).location(SLEEPING_ROOM_2).build()).build();
    public static final ActivitySchemaSourceDto READING = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("reading").locationProbability(SLEEPING_ROOM_1, 0.5).locationProbability(SLEEPING_ROOM_2, 0.05).preferredTempInLocation(LIVING_ROOM, 22d).preferredTempInLocation(LIVING_ROOM, 22d).mediaUsageSourceDto(MediaUsageSourceDto.builder().mediaType(ELECTRICITY).usagePerMinute(1d).location(LIVING_ROOM).build()).build();
    public static final ActivitySchemaSourceDto SHOPPING = ActivitySchemaSourceDto.builder().averageElapsedTime(60d).name("doing shopping").build();
    public static final ActivitySchemaSourceDto AT_WORK = ActivitySchemaSourceDto.builder().averageElapsedTime(8.5 * 60d).name("at work outside " + "house").build();
}
