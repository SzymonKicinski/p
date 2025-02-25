/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.domain;

public interface EventPublisher<E extends InternalEvent> {

    void send(final E event);
}
