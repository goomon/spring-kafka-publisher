package dev.iitp.publisher.producer.callback;

import dev.iitp.publisher.model.Latency;
import dev.iitp.publisher.model.chest.SensorRecord;
import dev.iitp.publisher.repository.LatencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@Transactional
@Component
@RequiredArgsConstructor
public class WatchSensorDataProducerCallback implements KafkaSendCallback<String, SensorRecord> {

    @Value("${user.nodeId}")
    private int nodeId;
    private final LatencyRepository latencyRepository;

    @Override
    public void onFailure(KafkaProducerException ex) {
    }

    @Override
    public void onSuccess(SendResult<String, SensorRecord> result) {
        SensorRecord sensorRecord = result.getProducerRecord().value();
        long timestamp = Instant.now().toEpochMilli();
        log.info("[Success] {} {} {}", sensorRecord.getUserId(), sensorRecord.getTimestamp(), timestamp - result.getRecordMetadata().timestamp());
//        saveLatency(timestamp - result.getRecordMetadata().timestamp());
    }

    private void saveLatency(long elapsed) {
        System.out.println("elapsed = " + elapsed);
        Latency latency = new Latency();
        latency.setTimestamp(LocalDateTime.now());
        latency.setLatencyMilli(elapsed);
        latency.setNodeId(nodeId);
        latencyRepository.save(latency);
    }
}
