package com.tfl.gov.uk.application;

import com.tfl.gov.uk.configuration.AirQualityConfiguration;
import com.tfl.gov.uk.service.AirQualityService;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;


/**
 * Created by SATBIM on 10/8/2016.
 */
public class AirQualityApplication extends Application<AirQualityConfiguration> {

    public static void main(String[] args) throws Exception {
        new AirQualityApplication().run(args);
    }

    @Override
    public void run(AirQualityConfiguration configuration, Environment environment) throws Exception {
        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration.getHttpClientConfiguration())
                .build("airQuality-httpClient");
        environment.jersey().register(new AirQualityService(httpClient));
    }
}

