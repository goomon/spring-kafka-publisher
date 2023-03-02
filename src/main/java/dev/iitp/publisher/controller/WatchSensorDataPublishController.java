package dev.iitp.publisher.controller;

import dev.iitp.publisher.model.SensorRecord;
import dev.iitp.publisher.service.WatchSensorDataProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WatchSensorDataPublishController {

    private final String TOPIC_NAME = "data";
    private final WatchSensorDataProducer watchSensorDataProducer;

    @PostMapping("/")
    public String publish(@RequestBody SensorRecord sensorRecord) {
        watchSensorDataProducer.async(TOPIC_NAME, sensorRecord);
        return "ok";
    }
}
