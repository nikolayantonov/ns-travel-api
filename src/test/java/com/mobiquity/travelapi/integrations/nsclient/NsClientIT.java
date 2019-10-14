package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.rest.model.TravelRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Test;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class NsClientIT {

    @Autowired
    private NsClient nsClient;

    @Value("${authentication.key.name.ns}")
    private String expectedKeyName;
    @Value("${authentication.key.value.ns}")
    private String expectedKeyValue;
    @Value("${urls.base.ns}")
    private String nsBaseUri;
    @Value("${urls.path.ns}")
    private String uriPath;


    @Test
    public void nsClientGetMethodSendsRequestToNsAndGetsResponse() {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        assertEquals(200, nsClient.get(travelRequest).getStatusCode().value());
    }
}
