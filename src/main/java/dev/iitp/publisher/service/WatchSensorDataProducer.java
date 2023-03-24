package dev.iitp.publisher.service;

import dev.iitp.publisher.model.SensorRecord;
import dev.iitp.publisher.producer.callback.WatchSensorDataProducerCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class WatchSensorDataProducer {

    private final WatchSensorDataProducerCallback watchSensorDataProducerCallback;
    private final KafkaTemplate<String, SensorRecord> watchSensorDataKafkaTemplate;

    public void async(String topic, SensorRecord message) {
        ListenableFuture<SendResult<String, SensorRecord>> future = watchSensorDataKafkaTemplate.send(topic, message.getUserId(), message);
        future.addCallback(watchSensorDataProducerCallback);
    }
}
