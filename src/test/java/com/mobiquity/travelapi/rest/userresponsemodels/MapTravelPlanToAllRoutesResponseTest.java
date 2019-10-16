package com.mobiquity.travelapi.rest.userresponsemodels;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.travelmodel.TravelPlan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class MapTravelPlanToAllRoutesResponseTest {


    private NsClient nsClient;

    private static NsResponse nsResponse;
    private static TravelPlan travelPlan ;
    private AllRoutesResponse allRoutesResponse;
    private AllRoutesResponse.Route firstRoute;
    private TravelModelMapper travelModelMapper = new TravelModelMapper();
    private MapTravelPlanToAllRoutesResponse mapTravelPlanToAllRoutesResponse = new MapTravelPlanToAllRoutesResponse();

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
    void setup() {

        travelPlan = travelModelMapper.mapToTravelPlan(nsResponse);
        allRoutesResponse = mapTravelPlanToAllRoutesResponse.mapToAllRoutesResponse(travelPlan);
        firstRoute = allRoutesResponse.getAvailableRoutes().get(0);
    }
    @Test
    void checkAllRoutesMappedFromTravelModel() {
        int expectedFirstRouteDuration = firstRoute.getDuration();
        assertEquals(expectedFirstRouteDuration, travelPlan.getRoutes().get(0).getPlannedDuration());
    }


}