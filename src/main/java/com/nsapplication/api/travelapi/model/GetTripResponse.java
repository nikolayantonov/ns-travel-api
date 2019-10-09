package com.nsapplication.api.travelapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTripResponse {

    private List<Route> trips;
    private String scrollRequestBackwardContext;
    private  String scrollRequestForwardContext;

}
