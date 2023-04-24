package dev.iitp.publisher.controller;

import dev.iitp.publisher.model.chest.SensorRecord;
import dev.iitp.publisher.service.WatchSensorDataProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WatchSensorDataPublishController {

    @Value("${user.topic}")
    private String TOPIC_NAME;
    private final WatchSensorDataProducer watchSensorDataProducer;

    @PostMapping("/")
    public String publish(@RequestBody SensorRecord chestSensorRecord) {
        watchSensorDataProducer.async(TOPIC_NAME, chestSensorRecord);
        return "ok";
    }
}
