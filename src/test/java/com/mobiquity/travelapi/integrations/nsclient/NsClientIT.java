package com.mobiquity.travelapi.integrations.nsclient;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
class NsClientIT {

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
    void nsClientGetMethodSendsRequestToNsAndGetsResponse() {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        assertEquals(200, nsClient.getNsResponse(travelRequest).getStatusCode().value());
    }

    @Test
    void nsResponseMapsToNsTripResponse()
    {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
        assertNotNull(Objects.requireNonNull(nsClient.getNsResponse(travelRequest).getBody()).getNsRoutes().get(0));
    }

    @Test
    void checkIfTravelRequestIsNull()
    {
        assertThrows(NullPointerException.class,() -> nsClient.getNsResponse(null).getBody() );

    }

    @Test
    void mapNsResponseToInternalModel()
    {
        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
        TravelPlan travelPlan = nsClient.getTravelPlan(travelRequest);
        assertEquals(6, travelPlan.getRoutes().size());
    }
}
