/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Sensor Data
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData {
    private double temperature;
    private int humidity;
    private long timestamp;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", timestamp=" + timestamp +
                '}';
    }

    public static SensorData create(final double temperature, final int humidity, final long timestamp) {
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(temperature);
        sensorData.setHumidity(humidity);
        sensorData.setTimestamp(timestamp);
        return sensorData;
    }
}