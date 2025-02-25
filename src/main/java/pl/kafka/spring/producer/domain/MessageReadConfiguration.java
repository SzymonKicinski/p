/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.domain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MessageReadConfiguration {

    @Bean
    public MessageReadFacade messageReadFacade(@Qualifier("kafkaSyncEventPublisher") final EventPublisher eventPublisher) {
        return new MessageReadFacade(eventPublisher);
    }
}
