package dev.iitp.publisher.model;

import lombok.Data;

@Data
public class SensorMeanRecord {

    private String userId;
    private long timestamp;
    private Axis acc;
    private double bvp;
    private double eda;
    private double hr;
    private double temp;
}
