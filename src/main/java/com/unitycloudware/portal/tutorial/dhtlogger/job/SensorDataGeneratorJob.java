/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.job;

import java.util.Map;

import org.nsys.util.JsonUtils;
import org.nsys.util.RandomRange;
import org.nsys.util.TimeUtils;
import org.nsys.daemon.scheduler.job.AbstractJob;

import com.unitycloudware.core.model.DataStream;
import com.unitycloudware.core.model.Device;
import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.core.service.DeviceManager;

import com.unitycloudware.portal.tutorial.dhtlogger.util.TestDataUtils;
import com.unitycloudware.portal.tutorial.dhtlogger.model.SensorData;

/**
 * Sensor Data Generator Job
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class SensorDataGeneratorJob extends AbstractJob {
    private DeviceManager deviceManager;
    private DataManager dataManager;

    public DeviceManager getDeviceManager() {
        return deviceManager;
    }

    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void execute(final Map<String, Object> jobDataMap) {
        getLog().info("Executing sensor data generator...");

        if (!jobDataMap.containsKey(ComponentName.DEVICE_MANAGER)) {
            getLog().error("Unable to find DeviceManager component in jobDataMap!");
            return;
        }

        if (!jobDataMap.containsKey(ComponentName.DATA_MANAGER)) {
            getLog().error("Unable to find DataManager component in jobDataMap!");
            return;
        }

        setDeviceManager((DeviceManager) jobDataMap.get(ComponentName.DEVICE_MANAGER));
        setDataManager((DataManager) jobDataMap.get(ComponentName.DATA_MANAGER));

        generateData();
    }

    public void generateData() {
        Device device = getDeviceManager().getDeviceByName(TestDataUtils.PROJECT_KEY, TestDataUtils.DEVICE_NAME);

        if (device == null) {
            return;
        }

        DataStream dataStream = getDataManager().getDataStream(TestDataUtils.PROJECT_KEY, TestDataUtils.DATA_STREAM_NAME);

        if (dataStream == null) {
            return;
        }

        SensorData data = SensorData.create(
                RandomRange.getRandomDouble(0, 100), RandomRange.getRandomDouble(0, 100), TimeUtils.getNow().getTime());

        String payload = JsonUtils.toJson(data);

        getLog().debugFormat("Storing generated sensor data... Payload: %s", payload);
        getDataManager().storeStream(dataStream, device, payload);
    }
}