package com.frotscher.example.jax2019;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Timer;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;

@Path("/hello")
@Singleton
public class HelloController {

    @Inject
    @Metric(name="msgCounter")
    Counter msgCounter;

    @Inject
    @Metric(name="msgTimer")
    Timer msgTimer;


    @GET
    @Produces(MediaType.APPLICATION_JSON)

    // addition information for OpenAPI documentation
    @Operation(summary = "Returns a timestamped message")
    @APIResponse(
            responseCode = "200",
            description = "Successful message response",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Message.class))
            })
    public Response sayHello() {

        // Use Timer of Metrics API for monitoring the creation of messages
        // The constructor of class Message simulates a slow object creation
        Timer.Context timerCtx = msgTimer.time();
        Message msg = new Message("Hallo JAX 2019", ZonedDateTime.now());
        timerCtx.close();

        // Use Counter of Metrics API to monitor the total number of created messages
        msgCounter.inc();

        return Response.ok().entity(msg).build();
    }

    @POST
    public Response receiveAnyData(String body) {
        System.out.println("Received request: " + body);
        return Response.ok().build();
    }
}
