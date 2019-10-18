package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.integrations.weather.Weather;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TravelServiceIT {

    @Autowired
    private TravelService travelService;
    @Autowired
    private WeatherClient weatherClient;
    private TravelRequest travelRequest;

    @BeforeEach
    void setup() {
        travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
    }

    @Test
    void checkTravelPlanWhenPassedToTravelPlanIsNotNull() {
        travelService.getTravelPlanFromNs(travelRequest);

        assertNotNull(travelService.getTravelPlanFromNs(travelRequest));
    }


    @Test
    void mapOfRoutesAndWeatherReturnedFromTravelService() {

    }

    @Test
    void listOfRoutesCreatedFromTravelPlan() {

        List<Route> routes = travelService.getTravelPlanFromNs(travelRequest).getRoutes();

    }

//    @Test
//    void weatherResponseEntityReturnedFromWeatherClient() {
//        ResponseEntity<Weather> weatherResponseEntity = weatherClient.getDarkSkyResponse(longitude, latitude, dateTime);
//    }

    //Check size of map -- Number of weather = number of route

}