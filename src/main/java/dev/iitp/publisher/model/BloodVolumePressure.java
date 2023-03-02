package dev.iitp.publisher.model;

import lombok.Data;

import java.util.List;

@Data
public class BloodVolumePressure {

    private int hz;
    private List<Double> value;
}
