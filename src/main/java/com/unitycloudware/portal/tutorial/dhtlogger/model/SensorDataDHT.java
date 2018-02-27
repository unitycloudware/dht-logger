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
public class SensorDataDHT {
    private double temperature;
    private double humidity;
    private double heatIndex;
    private long timestamp;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(double heatIndex) {
        this.heatIndex = heatIndex;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorDataDHT{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", heatIndex=" + heatIndex +
                ", timestamp=" + timestamp +
                '}';
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