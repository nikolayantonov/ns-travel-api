package com.mobiquity.travelapi.rest.userresponsemodels;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//model class to show response to user
@Builder
@Getter

public class AllRoutesResponse {

    private String origin;
    private String destination;
    private List<Route> availableRoutes;

    public AllRoutesResponse() {
    }

    public AllRoutesResponse(String origin, String destination, List<Route> availableRoutes) {
        this.origin = origin;
        this.destination = destination;
        this.availableRoutes = availableRoutes;
    }

    @Builder
    @Getter
    static class Route
    {
        private String startTime;
        private String arrivalTime;
        private int duration;
        private  int numberOfLegs;

        public Route() {
        }

        public Route(String startTime, String arrivalTime, int duration, int numberOfLegs) {
            this.startTime = startTime;
            this.arrivalTime = arrivalTime;
            this.duration = duration;
            this.numberOfLegs = numberOfLegs;
        }
    }

}
