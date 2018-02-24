/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.plugin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nsys.event.Event;
import org.nsys.plugin.PluginContext;
import org.nsys.system.ComponentProvider;
import org.nsys.system.ServiceProvider;
import org.nsys.util.TimeUtils;
import org.nsys.util.ConfigurationManager;
import org.nsys.daemon.event.SystemStartedEvent;
import org.nsys.daemon.plugin.AbstractManagementAgentPlugin;
import org.nsys.daemon.scheduler.SchedulerService;
import org.nsys.portal.event.PortalStartedEvent;

import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.core.service.DeviceManager;
import com.unitycloudware.portal.tutorial.dhtlogger.job.ComponentName;
import com.unitycloudware.portal.tutorial.dhtlogger.job.SensorDataGeneratorJob;
import com.unitycloudware.portal.tutorial.dhtlogger.DHTLoggerConfig;
import com.unitycloudware.portal.tutorial.dhtlogger.util.TestDataUtils;

/**
 * DHT Logger Plugin
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class DHTLoggerPlugin extends AbstractManagementAgentPlugin {

    public static final String DASHBOARD_VIEW = "nsys.portal.dashboard.view.default";
    public static final String DASHBOARD_VIEW_DEFAULT = "ucw-dhtlogger";

    @Override
    public void load(final PluginContext context) {
        getLog().debugFormat("Starting plugin %s", getName());

        DHTLoggerConfig.loadConfig();

        addComponents();
        scheduleJobs();
    }

    @Override
    public void unload(final PluginContext context) {
        getLog().debugFormat("Stopped plugin %s", getName());
    }

    @Override
    public void handleEvent(final PluginContext context, final Event event) {
        if (event != null) {

            if (event instanceof PortalStartedEvent) {
                // UCW Portal has been started successfully!
            }

            else if (event instanceof SystemStartedEvent) {
                configurePortalComponents();
            }
        }
    }

    protected void addComponents() {
    }

    protected void scheduleJobs() {
        // When the sensor data generator is disabled then skip adding job to scheduler.
        if (!DHTLoggerConfig.isGeneratorEnabled()) {
            return;
        }

        SchedulerService scheduler = ServiceProvider.getInstance().getServiceHost(SchedulerService.class);

        //Date delay1min = TimeUtils.addMinutes(TimeUtils.getNow(), 1);
        //long repeatInterval = (600 * 1000) * 2; // 2mins
        Date delay30sec = TimeUtils.addSeconds(TimeUtils.getNow(), 30);
        long repeatInterval = 10 * 1000; // 10sec

        Map<String, Object> jobDataMap = new HashMap<String, Object>();

        jobDataMap.put(ComponentName.DEVICE_MANAGER, ComponentProvider.getInstance().getComponent(DeviceManager.class));
        jobDataMap.put(ComponentName.DATA_MANAGER, ComponentProvider.getInstance().getComponent(DataManager.class));

        scheduler.scheduleJob(SensorDataGeneratorJob.class, jobDataMap, delay30sec, repeatInterval);
    }

    protected void configurePortalComponents() {
        customPortalConfig();
        createTestData();
    }

    protected void customPortalConfig() {
        Properties customPortalConfig = new Properties();
        customPortalConfig.setProperty(DASHBOARD_VIEW, DASHBOARD_VIEW_DEFAULT);

        ConfigurationManager.getInstance().merge(customPortalConfig, true);
    }

    protected void createTestData() {
        TestDataUtils testDataUtils = new TestDataUtils();
        testDataUtils.createData();
    }
}