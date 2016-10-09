package com.tfl.gov.uk.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by SATBIM on 10/8/2016.
 */
public class AirQualityConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClientConf = new HttpClientConfiguration();

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClientConf;
    }
}
