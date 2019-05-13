package com.frotscher.example.jax2019;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.ZonedDateTime;
import java.util.Random;

@Schema(requiredProperties = {"text", "created"}) // addition information for OpenAPI documentation
public class Message {

    private String text;
    private ZonedDateTime created;

    public Message(String text, ZonedDateTime created) {

        // Simulate slow creation of messages
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.text = text;
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public ZonedDateTime getCreated() {
        return created;
    }
}

