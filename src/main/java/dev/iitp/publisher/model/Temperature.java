package dev.iitp.publisher.model;

import lombok.Data;

import java.util.List;

@Data
public class Temperature {

    private int hz;
    private List<Double> value;
}
