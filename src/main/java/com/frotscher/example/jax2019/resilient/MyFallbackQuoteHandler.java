package com.frotscher.example.jax2019.resilient;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class MyFallbackQuoteHandler implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext context) {
        return "My most recent known quote";
    }
}
