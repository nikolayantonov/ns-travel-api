package com.mobiquity.travelapi.integrations.model;

import java.util.ArrayList;
import java.util.List;

public class TravelPlan {
    private String origin;
    private String destination;
    private String dateTime;

    private List<Route> routes;

    public TravelPlan(String origin, String destination, String dateTime) {
        this.origin = origin;
        this.destination = destination;
        this.dateTime = dateTime;
        routes = new ArrayList<>();
    }

    public void addToRoutes() {

    }
}
