package com.mobiquity.travelapi.integrations.stereotype;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TravelPlan {

    private String origin;
    private String destination;
    private String dateTime;

    private List<Route> routes;
}
