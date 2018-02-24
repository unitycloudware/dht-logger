/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger;

import java.util.Properties;

import org.nsys.util.ConfigurationManager;

/**
 * DHT Logger Config
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class DHTLoggerConfig {

    public static final String CONFIG_NAME = "dht-logger.cfg";
    public static final String CONFIG_NAME_INTERNAL = "dht-logger.cfg.internal";
    public static final String VERSION = "ucw.dhtlogger.version";
    public static final String PLUGIN_KEY = "ucw.dhtlogger.pluginKey";
    public static final String GENERATOR_ENABLED = "ucw.dhtlogger.generator.enabled";

    public static void loadConfig() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        Properties props = config.loadConfig(String.format("/%s", CONFIG_NAME), DHTLoggerConfig.class);

        if (props != null) {
            config.merge(props);
        }

        props = config.loadConfig(String.format("/%s", CONFIG_NAME_INTERNAL), DHTLoggerConfig.class);

        if (props != null) {
            config.merge(props, true);
        }
    }

    public static String getVersion() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getVersion(version);
    }

    public static String getBuildNumber() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getBuildNumber(version);
    }

    public static String getPluginKey() {
        return ConfigurationManager.getInstance().getProperty(PLUGIN_KEY);
    }

    public static boolean isGeneratorEnabled() {
        String generatorEnabled = System.getProperty(GENERATOR_ENABLED, "false");
        return Boolean.valueOf(generatorEnabled);
    }
}