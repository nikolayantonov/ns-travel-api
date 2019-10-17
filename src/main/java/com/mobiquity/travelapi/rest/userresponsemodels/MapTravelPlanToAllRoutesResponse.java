package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;

import java.util.ArrayList;
import java.util.List;

public class MapTravelPlanToAllRoutesResponse {

    public static AllRoutesResponse mapToAllRoutesResponse(TravelPlan travelPlan) {
        return new MapTravelPlanToAllRoutesResponse().map(travelPlan);
    }

    private AllRoutesResponse map(TravelPlan travelPlan) {
        return AllRoutesResponse.builder()
                .origin(travelPlan.getOrigin())
                .destination(travelPlan.getDestination())
                .availableRoutes(getListOfRoutes(travelPlan))
                .build();
    }

    private List<AllRoutesResponse.Route> getListOfRoutes(TravelPlan travelPlan) {
        return new ArrayList<AllRoutesResponse.Route>() {{
            travelPlan.getRoutes().forEach(
                    travelRoute -> add(buildRoute(travelRoute))
            );
        }};
    }

    private AllRoutesResponse.Route buildRoute( Route route) {
        return AllRoutesResponse.Route.builder()
                .startTime(route.getOrigin().getPlannedDepartureTime())
                .arrivalTime(route.getDestination().getPlannedArrivalTime())
                .duration(route.getPlannedDuration())
                .numberOfLegs(route.getLegs().size())
                .build();
    }

}
