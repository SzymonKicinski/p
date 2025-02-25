/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import pl.kafka.spring.producer.domain.EventPublisher;
import pl.kafka.spring.producer.domain.InternalEvent;

@Configuration
class KafkaEventPublisherConfiguration {

    @Bean
    EventPublisher kafkaAsyncEventPublisher(final KafkaTemplate<String, InternalEvent> kafkaTemplate) {
        return new KafkaAsyncEventPublisher(kafkaTemplate);
    }

    @Bean
    EventPublisher kafkaSyncEventPublisher(final KafkaTemplate<String, InternalEvent> kafkaTemplate) {
        return new KafkaSyncEventPublisher(kafkaTemplate);
    }
}
