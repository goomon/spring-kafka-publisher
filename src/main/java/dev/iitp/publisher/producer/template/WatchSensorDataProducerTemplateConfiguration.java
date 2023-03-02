package dev.iitp.publisher.producer.template;

import dev.iitp.publisher.model.SensorRecord;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WatchSensorDataProducerTemplateConfiguration {

    private final KafkaProperties properties;

    @Bean
    public KafkaTemplate<String, SensorRecord> watchSensorDataKafkaTemplate() {
        return new KafkaTemplate<>(watchSensorDateProducerFactory());
    }

    @Bean
    public ProducerFactory<String, SensorRecord> watchSensorDateProducerFactory() {
        return new DefaultKafkaProducerFactory<>(watchSensorDataProducerConfig());
    }

    @Bean
    public Map<String, Object> watchSensorDataProducerConfig() {
        Map<String, Object> props = this.properties.buildProducerProperties();
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
