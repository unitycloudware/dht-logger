/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Sensor Data from DHTx sensor
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorDataDHT extends SensorData {
    private double heatIndex;

    public double getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(double heatIndex) {
        this.heatIndex = heatIndex;
    }

    @Override
    public String toString() {
        return "SensorDataDHT{" +
                "heatIndex=" + heatIndex +
                "} " + super.toString();
    }

    public static SensorDataDHT create(
            final double temperature,
            final double humidity,
            final double heatIndex,
            final long timestamp) {

        SensorDataDHT sensorDataDHT = new SensorDataDHT();
        sensorDataDHT.setTemperature(temperature);
        sensorDataDHT.setHumidity(humidity);
        sensorDataDHT.setHeatIndex(heatIndex);
        sensorDataDHT.setTimestamp(timestamp);

        return sensorDataDHT;
    }
}