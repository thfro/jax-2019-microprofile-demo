package com.frotscher.example.jax2019.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/config")
@RequestScoped
public class ConfigTestController {

    @Inject
    @ConfigProperty(name = "injected.value")
    private String injectedValue;


    @Inject
    @ConfigProperty(name = "MY_CONFIG_VALUE_2", defaultValue = "my default value")
    private String myConfigValue;

    @Inject
    @ConfigProperty(name = "VALUE_FROM_DB_1")
    private String myDbValue;




    @Path("/injected")
    @GET
    public String getInjectedConfigValue() {
        return "Config value as Injected by CDI " + injectedValue;
    }

    @Path("/myConfigValue")
    @GET
    public String getMyConfigValue() {
        return myConfigValue;
    }

    @Path("/myDbValue")
    @GET
    public String getMyDbValue() {
        return myDbValue;
    }



    @Path("/lookup")
    @GET
    public String getLookupConfigValue() {
        Config config = ConfigProvider.getConfig();
        String value = config.getValue("value", String.class);
        return "Config value from ConfigProvider " + value;
    }
}
