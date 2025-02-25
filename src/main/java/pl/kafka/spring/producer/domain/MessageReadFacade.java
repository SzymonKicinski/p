/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.domain;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.kafka.spring.event.MessageReadEvent;
import pl.kafka.spring.producer.web.MessageReadRequest;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MessageReadFacade {

    EventPublisher eventPublisher;

    public void sendEvent(final MessageReadRequest messageReadRequest) {
        final var eventValue = MessageReadEvent.fromMessageReadRequest(messageReadRequest);
        eventPublisher.send(eventValue);
    }
}
