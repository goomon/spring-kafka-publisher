package dev.iitp.publisher.producer.callback;

import dev.iitp.publisher.model.SensorRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.support.SendResult;

import java.time.Instant;

@Slf4j
public class WatchSensorDataProducerCallback implements KafkaSendCallback<String, SensorRecord> {

    @Override
    public void onFailure(KafkaProducerException ex) {
    }

    @Override
    public void onSuccess(SendResult<String, SensorRecord> result) {
        SensorRecord sensorRecord = result.getProducerRecord().value();
        long timestamp = Instant.now().toEpochMilli();
        log.info("[Success] {} {} {}", sensorRecord.getUserId(), sensorRecord.getTimestamp(), timestamp - result.getRecordMetadata().timestamp());
    }
}
