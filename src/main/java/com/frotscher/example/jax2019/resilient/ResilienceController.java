package com.frotscher.example.jax2019.resilient;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Random;

@Path("/resilience")
@ApplicationScoped
public class ResilienceController {

    @Timeout(500)
    @Fallback(MyFallbackHandler.class)
    @GET
    public String checkTimeout() {
        try {
            Thread.sleep(700L);
        } catch (InterruptedException e) {
            //
        }
        return "Never from normal processing";
    }

    @GET
    @Path("retry")
    @Retry(maxRetries = 4)
    @Fallback(MyFallbackQuoteHandler.class)
    public String getQuoteForProduct() {

        int quote = new Random().nextInt(1000);

        if (quote > 333) {
            System.out.println("Too expensive: " + quote);
            throw new RuntimeException("Too expensive: " + quote);
        }

        return String.valueOf(quote);
    }

}
