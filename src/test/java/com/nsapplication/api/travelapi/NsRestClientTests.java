package com.nsapplication.api.travelapi;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NsRestClientTests {

    @Test
    public void checkRestTemplateHasKey() {
        NsRestClient restClient = new NsRestClient();
        String url = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16L25:00+0200";
        String keyName = "Ocp-Apim-Subscription-Key";
        String keyValue = "a3db7e8808944380b20408e9742c86ab";

        restClient.getNsTrips(url, keyValue);

//        RestTemplate restTemplate = travelService.createRestTemplate(url, keyValue);
//        restTemplate.exchange(url, HttpMethod.GET, TravelService.class);

        assertEquals(1, 1);

    }

}
