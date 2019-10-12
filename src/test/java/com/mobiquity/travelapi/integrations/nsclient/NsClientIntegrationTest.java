package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.rest.model.TravelRequest;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class NsClientIntegrationTest {

    @Autowired
    NsClient nsClient;

    @Value("${authentication.key.name.ns}")     private String expectedKeyName;
    @Value("${authentication.key.value.ns}")    private String expectedKeyValue;
    @Value("${urls.base.ns}")                   private String nsBaseUri;
    @Value("${urls.path.ns}")                   private String uriPath;


    @Test
    public void nsClientGetMethodSendsRequestToNsAndGetsResponse() {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        assertEquals(200, nsClient.get(travelRequest).getStatusCode().value());
//        assertTrue(nsClient.get(travelRequest));
    }
}
