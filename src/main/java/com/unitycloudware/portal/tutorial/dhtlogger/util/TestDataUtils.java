/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.util;

import org.nsys.logging.Log;
import org.nsys.system.ComponentProvider;

import com.unitycloudware.core.model.Device;
import com.unitycloudware.core.model.DeviceType;
import com.unitycloudware.core.model.DataStream;
import com.unitycloudware.core.model.DataStreamType;
import com.unitycloudware.core.model.Project;
import com.unitycloudware.core.model.StorageType;
import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.core.service.DeviceManager;
import com.unitycloudware.core.service.ProjectManager;

/**
 * Test Data Utils
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class TestDataUtils {
    private static final Log log = Log.getLogger(TestDataUtils.class);
    private ProjectManager projectManager;
    private DeviceManager deviceManager;
    private DataManager dataManager;

    public static final String PROJECT_KEY = "UDL";
    public static final String PROJECT_NAME = "UCW-DHT-Logger";
    public static final String PROJECT_DESC = "Data logger for DHTx sensors";
    public static final String DEVICE_NAME = "adafruit-feather-m0-wifi";
    public static final String DEVICE_DESC = "This is a test device.";
    public static final String DATA_STREAM_NAME = "ucw-dhtlogger";
    public static final String DATA_STREAM_DESC = "Temperature and humidity data collected from DHTx sensors.";

    public ProjectManager getProjectManager() {
        if (projectManager == null) {
            projectManager = ComponentProvider.getInstance().getComponent(ProjectManager.class);
        }

        return projectManager;
    }

    public DeviceManager getDeviceManager() {
        if (deviceManager == null) {
            deviceManager = ComponentProvider.getInstance().getComponent(DeviceManager.class);
        }

        return deviceManager;
    }

    public DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = ComponentProvider.getInstance().getComponent(DataManager.class);
        }

        return dataManager;
    }

    public void createData() {
        log.debug("Creating test data...");

        createProject();
        createDevice();
        createDataStream();
    }

    protected void createProject() {
        log.debugFormat("Creating project '%s' with key '%s'...", PROJECT_NAME, PROJECT_KEY);

        if (getProjectManager().getProjectByKey(PROJECT_KEY) != null) {
            return;
        }

        Project project = new Project();
        project.setKey(PROJECT_KEY);
        project.setName(PROJECT_NAME);
        project.setDescription(PROJECT_DESC);

        getProjectManager().addProject(project);

    }

    protected void createDevice() {
        log.debugFormat("Creating device '%s' for project '%s'...", DEVICE_NAME, PROJECT_KEY);

        Project project = getProjectManager().getProjectByKey(PROJECT_KEY);

        if (project == null || getDeviceManager().getDeviceByName(PROJECT_KEY, DEVICE_NAME) != null) {
            return;
        }

        Device device = getDeviceManager().createDevice(
                DEVICE_NAME, DEVICE_DESC, DeviceType.GENERIC.name(), 120, PROJECT_KEY);

        getDeviceManager().addDevice(device);
    }

    protected void createDataStream() {
        log.debugFormat("Creating data stream '%s' for project '%s'...", DATA_STREAM_NAME, PROJECT_KEY);

        Project project = getProjectManager().getProjectByKey(PROJECT_KEY);

        if (project == null || getDataManager().getDataStream(PROJECT_KEY, DATA_STREAM_NAME) != null) {
            return;
        }

        DataStream dataStream = new DataStream();
        dataStream.setName(DATA_STREAM_NAME);
        dataStream.setDescription(DATA_STREAM_DESC);
        dataStream.setProject(project);
        dataStream.setType(DataStreamType.DATA_MESSAGE);
        dataStream.setStorageType(StorageType.GENERIC.name());
        dataStream.setEnabled(true);

        getDataManager().addDataStream(dataStream);
    }
}