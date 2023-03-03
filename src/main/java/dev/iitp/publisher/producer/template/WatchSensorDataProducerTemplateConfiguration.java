package dev.iitp.publisher.producer.template;

import dev.iitp.publisher.model.SensorRecord;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerProducerListener;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WatchSensorDataProducerTemplateConfiguration {

    private final KafkaProperties properties;

    @Bean
    public KafkaTemplate<String, SensorRecord> watchSensorDataKafkaTemplate(ProducerFactory<String, SensorRecord> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, SensorRecord> watchSensorDateProducerFactory(MeterRegistry meterRegistry,
                                                                                PrometheusMeterRegistry prometheusMeterRegistry) {
        Map<String, Object> props = watchSensorDataProducerConfig();
        DefaultKafkaProducerFactory<String, SensorRecord> producerFactory = new DefaultKafkaProducerFactory<>(props);
        producerFactory.addListener(new MicrometerProducerListener<>(meterRegistry));
        producerFactory.addListener(new MicrometerProducerListener<>(prometheusMeterRegistry));
        return producerFactory;
    }

    private Map<String, Object> watchSensorDataProducerConfig() {
        Map<String, Object> props = this.properties.buildProducerProperties();
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }
}
