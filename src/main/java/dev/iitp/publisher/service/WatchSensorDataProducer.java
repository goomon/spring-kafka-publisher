package dev.iitp.publisher.service;

import dev.iitp.publisher.model.SensorRecord;
import dev.iitp.publisher.producer.callback.WatchSensorDataProducerCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@RequiredArgsConstructor
public class WatchSensorDataProducer {

    private final KafkaTemplate<String, SensorRecord> watchSensorDataKafkaTemplate;

    public void async(String topic, SensorRecord message) {
        ListenableFuture<SendResult<String, SensorRecord>> future = watchSensorDataKafkaTemplate.send(topic, message.getUserId(), message);
        future.addCallback(new WatchSensorDataProducerCallback());
    }
}
