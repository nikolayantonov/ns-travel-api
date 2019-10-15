package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MapNsResponseToModelTest {

    @Test
    public void whenGetListOfRoutesFromResponseCalledReturnListOfRoutes() {
        NsResponse nsResponse = new NsResponse();

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            nsResponse = objMapper.readValue(new File("./src/test/java/resources/DummyNsResponse.json"), NsResponse.class);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        int expectedFirstRouteDuration = nsResponse.getRoutes().get(0).getPlannedDuration();

        com.mobiquity.travelapi.integrations.stereotype.TravelPlan travelPlan = MapNsResponseToModel.map(nsResponse);

        assertEquals(expectedFirstRouteDuration, travelPlan.getRoutes().get(0).getPlannedDuration());

    }

}