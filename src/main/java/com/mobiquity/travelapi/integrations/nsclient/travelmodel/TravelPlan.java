package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
public class TravelPlan {

    private String origin;
    private String destination;
    private List<Route> routes;

}
