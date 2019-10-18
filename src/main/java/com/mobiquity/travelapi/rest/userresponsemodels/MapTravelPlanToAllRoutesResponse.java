package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MapTravelPlanToAllRoutesResponse {

    @Autowired
    private WeatherClient weatherClient;

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

    private AllRoutesResponse.Route buildRoute(Route route) {
        return AllRoutesResponse.Route.builder()
                .startTime(route.getOrigin().getPlannedDepartureTime())
                .arrivalTime(route.getDestination().getPlannedArrivalTime())
                .duration(route.getPlannedDuration())
                .numberOfLegs(route.getLegs().size())
             //   .weather(weatherClient.getDarkSkyResponse(route.getLegs().get(0).getStops().get(0).getLatitude())) not sure how to build here weather response
                .build();
    }

}
