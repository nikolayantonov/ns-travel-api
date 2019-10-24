package com.mobiquity.travelapi.rest.userresponsemodels;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.integrations.weather.Weather;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTravelPlanToAllRoutesResponseTest {

    private static NsResponse nsResponse;
    private static Weather weatherDestination;
    private static TravelPlan travelPlan ;
    private AllRoutesResponse.RouteWeather firstRoute;
    private static Weather weatherOrigin;

    @BeforeAll
     static void setUp() {

        Weather.Currently originCurrentWeather = new Weather.Currently("Overcast",57.01,0.77,11.48);
        weatherOrigin = new Weather(originCurrentWeather);

        Weather.Currently destinationCurrentWeather = new Weather.Currently("Overcast",51.71,0.88,12.8);
        weatherDestination  =new Weather(destinationCurrentWeather);

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

        travelPlan = TravelModelMapper.mapToTravelPlan(nsResponse);
        List<RouteAndWeather> rw = new ArrayList<>();
        for (Route route : travelPlan.getRoutes()) {
            rw.add(RouteAndWeather.builder().route(route)
                .weatherAtDestination(weatherDestination)
                 .weatherAtOrigin(weatherOrigin)
                .build());
        }
        AllRoutesResponse allRoutesResponse = MapTravelPlanToAllRoutesResponse.mapToAllRoutesResponse(rw);
        firstRoute = allRoutesResponse.getAvailableRoutes().get(0);
    }

    @Test
    void checkAllRoutesMappedFromTravelModel() {
        int expectedFirstRouteDuration = 48;
        assertEquals(expectedFirstRouteDuration, firstRoute.getDuration());
    }

    @Test
    void checkThatWeatherMappedFromWeatherModel() {
        double expectedHumidity = weatherDestination.getCurrently().getHumidity();
        assertEquals(expectedHumidity, firstRoute.getWeatherAtDestination().getHumidity());
    }

    @Test
    void checkThatWeatherAtOriginMappedFromWeatherModel() {

       // double expectedHumidity = weatherOrigin.getCurrently().getHumidity();
        assertEquals(0.77, firstRoute.getWeatherAtOrigin().getHumidity());
    }

}