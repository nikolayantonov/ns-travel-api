package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;

import java.util.ArrayList;
import java.util.List;

public class MapTravelPlanToAllRoutesResponse {

    public AllRoutesResponse mapToAllRoutesResponse(TravelPlan travelPlan)
    {
        System.out.println("here......");
        return AllRoutesResponse.builder()
                .origin(travelPlan.getOrigin())
                .destination(travelPlan.getDestination())
                .availableRoutes(getListOfRoutes(travelPlan))
                .build();
    }

    // possible duplicate code. how to avoid?

    private List<AllRoutesResponse.Route> getListOfRoutes(TravelPlan travelPlan) {

        return new ArrayList<AllRoutesResponse.Route>() {{
            travelPlan.getRoutes().forEach(
                    travelRoute -> add(buildRoute(travelRoute))
            );
        }};
    }

    private AllRoutesResponse.Route buildRoute( Route route) {

        return AllRoutesResponse.Route.builder()
                .startTime(route.getDateTime())
                .arrivalTime(route.getDateTime())
                .duration(route.getPlannedDuration())
                .numberOfLegs(route.getLegs().size())
                .build();

    }

}
