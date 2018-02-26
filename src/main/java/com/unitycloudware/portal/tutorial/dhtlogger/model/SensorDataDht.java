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
public class SensorDataDht {
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
        return "SensorDataDht{" +
                ", humidity=" + humidity +
                "temperature=" + temperature +
                ", heatIndex=" + heatIndex +
                ", timestamp=" + timestamp +
                '}';
    }

    public static SensorDataDht create(final double humidity, final double temperature, final double heatIndex, final long timestamp) {
        SensorDataDht sensorDataDht = new SensorDataDht();
        sensorDataDht.setHumidity(humidity);
        sensorDataDht.setTemperature(temperature);
        sensorDataDht.setHeatIndex(heatIndex);
        sensorDataDht.setTimestamp(timestamp);
        return sensorDataDht;
    }
}