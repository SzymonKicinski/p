/*
 * Copyright (c)
 * Author: Szymon Kiciński
 */

package pl.kafka.spring.producer.web;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kafka.spring.producer.domain.MessageReadFacade;

import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping(path = "/message")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageReadController {

    MessageReadFacade messageReadFacade;

    @PostMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendFeedback(@RequestBody final MessageReadRequest messageReadRequest) {
        messageReadFacade.sendEvent(messageReadRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/read/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendManyFeedback(@RequestBody final MessageReadRequest messageReadRequest,
                                              @PathVariable Integer count) {
        requireNonNull(count);

        for (int i = 0; i < count; i++) {
            messageReadFacade.sendEvent(messageReadRequest);
        }

        return ResponseEntity.ok().build();
    }
}
