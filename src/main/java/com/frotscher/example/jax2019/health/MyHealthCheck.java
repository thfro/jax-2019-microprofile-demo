package com.frotscher.example.jax2019.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;

@Health
public class MyHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {

        // try to connect to some other service
        Client serviceClient =
                ClientBuilder.newBuilder().connectTimeout(1000, TimeUnit.MILLISECONDS).newClient();

        //WebTarget service = serviceClient.target("http://localhost:1234/myOtherService");
        WebTarget service = serviceClient.target("http://www.google.de");

        try {
            Response swResponse = service.request().get();
            if (swResponse.getStatus() == 200) {
                return HealthCheckResponse.named("My other service").up().build();
            } else {
                return HealthCheckResponse.named("My other service").down().withData("status", swResponse.getStatus()).build();
            }

        } catch (Exception ex) {
            return HealthCheckResponse.named("My other service").down().withData("status", ex.getMessage()).build();
        }
    }




}
