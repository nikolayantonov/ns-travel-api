package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.mobiquity.travelapi.integrations.stereotype.TravelPlan;

public class MapNsResponseToModel {

    public static TravelPlan map(NsResponse nsResponse) {

        TravelPlan travelPlan = TravelPlan.builder()
                        .origin(nsResponse.getRoutes().get(0).getLegs().get(0).getStartStation().getName())
                        .destination(nsResponse.getRoutes().get(0).getLegs().get(0).getEndStation().getName())
                .dateTime("hi"/*nsResponse.getRoutes().get(0).getLegs().get(0).getStops().get(0).*/)
                .routes(null)
                .build();

        return null;
    }
}
