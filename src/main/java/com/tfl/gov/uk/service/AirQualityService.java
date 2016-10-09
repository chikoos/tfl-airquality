package com.tfl.gov.uk.service;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * Created by SATBIM on 10/8/2016.
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1")
public class AirQualityService {

    private Logger logger = LoggerFactory.getLogger(AirQualityService.class);

    private HttpClient httpClient;

    public AirQualityService(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @GET
    @Path("/airquality")
    public String getAirQualityForAddress(@QueryParam("address") String address) {
        String output="";
        HttpResponse httpResponse = null;
        /** Getting latitude and longitude for the address */
        try {
            HttpGet httpGetReq = new HttpGet("https://maps.googleapis.com/maps/api/geocode/json?" +
                    "address="+ URLEncoder.encode(address, "UTF-8") + "&key=AIzaSyAIbRqXe-LJSa6j45bSmM4ASo-fob-9kvw&sensor=false&language=en");
            httpGetReq.addHeader("Accept", "application/json");
            httpResponse = httpClient.execute(httpGetReq);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Failed with status code " + httpResponse.getStatusLine().getStatusCode());
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            String line;
            while((line=bufferedReader.readLine())!= null) {
                output = output + line;
            }
        } catch (Exception anException) {
            logger.info(anException.getMessage());
        }
        return output;
    }
}
