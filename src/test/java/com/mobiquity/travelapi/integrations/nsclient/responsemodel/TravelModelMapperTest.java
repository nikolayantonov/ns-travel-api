package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.integrations.travelmodel.Route;
import com.mobiquity.travelapi.integrations.travelmodel.TravelPlan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TravelModelMapperTest {

    private TravelModelMapper travelModelMapper = new TravelModelMapper();
    private static NsResponse nsResponse;
    private TravelPlan travelPlan;
    private Route firstRoute;

    @BeforeAll
    static void setUp() {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            nsResponse = objMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"), NsResponse.class);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @BeforeEach
    void beforeMethod() {
        travelPlan = travelModelMapper.mapToTravelPlan(nsResponse);
        firstRoute = travelPlan.getRoutes().get(0);
    }

    @Test
    void checkTravelModelRoutesMappedFromNsResponseModel() {
        int expectedFirstRouteDuration = firstRoute.getPlannedDuration();
        assertEquals(expectedFirstRouteDuration, travelPlan.getRoutes().get(0).getPlannedDuration());
    }

    @Test
    void stopsCreatedFromNsResponseModelValid() {
        String expectedNameOfStopInSecondLeg = "Leiden Centraal";
        assertEquals(expectedNameOfStopInSecondLeg ,firstRoute.getLegs().get(0).getStops().get(1).getName());
    }
    @Test
    void stopsCreatedFromNsResponseModelInValid() {
        String incorrectName = "Not Leiden Centraal";
        assertNotEquals(incorrectName, firstRoute.getLegs().get(0).getStops().get(1).getName());
    }

    @Test
    void legsCreatedFromNsResponseModelFirstRouteValid() {
        int expectedNumberLegs = 5;
        assertEquals(expectedNumberLegs, travelPlan.getRoutes().get(0).getLegs().size());
    }
    @Test
    void legsCreatedFromNsResponseModelFirstRouteInvalid() {
        int incorrectNumberLegs = 7;
        assertNotEquals(incorrectNumberLegs, travelPlan.getRoutes().get(0).getLegs().size());
    }

    @Test
    void originCreatedFromNsResponseModelSecondRouteFirstLegValid() {
        String expectedDepartureTime = "2019-10-14T15:33:00+0200";
        assertEquals(expectedDepartureTime, travelPlan.getRoutes().get(1).getOrigin().getPlannedDepartureTime());
    }

}