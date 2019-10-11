package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.TravelRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TravelServiceTest {
    @Autowired
    TravelService travelService;
    @Test
    public void checkURIIsGeneratedFromTravelRequestIsValid()
    {
        TravelRequest travelRequest = new TravelRequest("8400282","8400056","2019-10-07T16L25:00+0200");
        assertEquals("https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?originEVACode=8400282&destinationEVACode=8400056&dateTime=2019-10-07T16L25:00+0200",
                travelService.generateURI(travelRequest).toString());
    }


}