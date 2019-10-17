package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TravelService {

    @Autowired
    private NsClient nsClient;

    public TravelPlan getTravelPlanFromNs(TravelRequest travelRequest) {
//        return nsClient.getTravelPlan(travelRequest);


        TravelPlan travelPlan = nsClient.getTravelPlan(travelRequest);
        Map<String, String> locationAndTime = getLocationAndTime(travelPlan);

    }

    private HashMap<String, String> getLocationAndTime(TravelPlan travelPlan) {
        Map<Route, Map<String, String>> routeDestinationAndTime = new HashMap<>();

//        {Route 1 :    origin      - weather
//                      destination - weather
//         Route 2 :    origin      - weather
//                      desination  - weather
//
//        }
        Route firstRoute = travelPlan.getRoutes().get(0);

        Map<Route, Map<String, String>> oneMap = new HashMap<>();

        travelPlan.getRoutes().forEach(
                route -> routeDestinationAndTime.put(route, Map.of(route.getOrigin().getStationName(), route.getOrigin().getPlannedDepartureTime(),
                        route.getDestination().getStationName(), route.getDestination().getPlannedArrivalTime()))
        );

//        travelPlan.getRoutes().forEach(
//                route -> maps.putAll(Map.of(route.getOrigin().getStationName(), route.getOrigin().getPlannedDepartureTime(),
//                        route.getDestination().getStationName(), route.getDestination().getPlannedArrivalTime()))
//        );





    }
}
