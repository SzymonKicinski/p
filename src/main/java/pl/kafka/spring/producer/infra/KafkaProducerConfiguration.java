/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.infra;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import pl.kafka.spring.producer.domain.InternalEvent;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
class KafkaProducerConfiguration {

    KafkaProperties kafkaProperties;

    @Bean("kafkaTemplate")
    public KafkaTemplate<String, InternalEvent> kafkaTemplate(final ProducerFactory<String, InternalEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, InternalEvent> producerFactory() {
        final var producerConfig = getProducerConfig();
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    private Map<String, Object> getProducerConfig() {
        final Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        properties.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, "5000");
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, "5000");

        properties.putAll(kafkaProperties.getSafeSettings());
        return properties;
    }
}
