package com.frotscher.example.jax2019.resilient;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

public class MyFallbackHandler implements FallbackHandler<String> {
    @Override
    public String handle(ExecutionContext context) {
        return "This is the value from my fallback handler";
    }
}
