package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.integrations.weather.Weather;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

//model class to show response to user
@Builder
@Getter
public class AllRoutesResponse {

    private String origin;
    private String destination;
    private List<Route> availableRoutes;

    @Builder
    @Getter
    static class Route
    {
        private String startTime;
        private String arrivalTime;
        private int duration;
        private  int numberOfLegs;
        private String summary;
        private double temperature;
        private Weather weather;
    }

}
