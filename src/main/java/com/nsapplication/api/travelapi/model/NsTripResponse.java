package com.nsapplication.api.travelapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NsTripResponse {

    @JsonAlias("trips")
    private List<Route> routes;
    private String scrollRequestBackwardContext;
    private  String scrollRequestForwardContext;
    @JsonAlias("firstDeparture") private String startStation;
    @JsonAlias("lastTripArrival") private String endStation;

}
