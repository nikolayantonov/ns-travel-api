package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.mobiquity.travelapi.integrations.stereotype.Itinerary;
import com.mobiquity.travelapi.integrations.stereotype.TravelPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//@Setter(value = AccessLevel.PRIVATE)
public class MapNsResponseToModel {

    private static MapNsResponseToModel mapper = null;


    public static TravelPlan map(NsResponse nsResponse) {

        mapper = getInstance();

        List<com.mobiquity.travelapi.integrations.stereotype.Route> stRoutes = mapper.getListOfRoutes(nsResponse);

//        TravelPlan travelPlan = TravelPlan.builder()
//                .origin(nsResponse.getRoutes().get(0).getLegs().get(0).getStartStation().getName())
//                .destination(nsResponse.getRoutes().get(0).getLegs().get(0).getEndStation().getName())
//                .routes(listOfRoute)
//                .build();

        return TravelPlan.builder()
                .routes(stRoutes)
                .build();
    }

    private List<com.mobiquity.travelapi.integrations.stereotype.Route> getListOfRoutes(NsResponse nsResponse) {
        List<com.mobiquity.travelapi.integrations.stereotype.Route> stRoutes = new ArrayList<>();
        int index = 0;

        for (Route route : nsResponse.getRoutes()) {
            //Itinerary
            //String --Found in legs
            //Itinerary
            //int
            //int
            //List of Legs
            //Bigdecimal

            com.mobiquity.travelapi.integrations.stereotype.Route stRoute = com.mobiquity.travelapi.integrations.stereotype.Route.builder()
                    .index(index++)
                    .origin(Itinerary.builder()
                            .name(route.getLegs().get(0).getStartStation().getName())
                            .plannedArrivalTime(null)
                            .plannedTrack(route.getLegs().get(0).getStartStation().getPlannedTrack())
                            .actualTrack(null)
                            .plannedDepartureTime(route.getLegs().get(0).getStartStation().getPlannedDateTime())
                            .build()
                    )  //Itinerary
                    .direction(null) //String --Found in legs
                    .destination(null) //Itinerary
                    .plannedDuration(route.getPlannedDuration()) //int
                    .numberOfTransfers(route.getLegs().size() - 1) //int
                    .legs(getListOfLegs(route.getLegs())) //List of Legs
                    .price(null) //Bigdecimal
                    .build();
            stRoutes.add(stRoute);
        }
        return stRoutes;
    }

    private List<com.mobiquity.travelapi.integrations.stereotype.Leg> getListOfLegs(List<Leg> legs) {
        List<com.mobiquity.travelapi.integrations.stereotype.Leg> stLegList = new ArrayList<>();
        for (Leg leg: legs) {
            com.mobiquity.travelapi.integrations.stereotype.Leg stLeg = com.mobiquity.travelapi.integrations.stereotype.Leg.builder()
                    .arrivalPlatform(leg.getEndStation().getPlannedTrack())
                    .departurePlatform(leg.getStartStation().getPlannedTrack())
                    .endStation(leg.getEndStation().getName())
                    .numberOfStops(leg.getStops().size())
                    .startStation(leg.getStartStation().getName())
                    .stops(getListOfStops(leg.getStops()))
                    .build();
            stLegList.add(stLeg);
        }

        return stLegList;
    }

    private List<com.mobiquity.travelapi.integrations.stereotype.Stop> getListOfStops(List<Stop> stops) {
        List<com.mobiquity.travelapi.integrations.stereotype.Stop> stStops = new ArrayList<>();
        for (Stop stop : stops) {
            com.mobiquity.travelapi.integrations.stereotype.Stop stStop = com.mobiquity.travelapi.integrations.stereotype.Stop.builder()
                    .name(stop.getName())
                    .build();
            stStops.add(stStop);
        }
        return stStops;
    }




    private MapNsResponseToModel() {
    }

    private static MapNsResponseToModel getInstance() {
        if (mapper == null) {
            mapper = new MapNsResponseToModel();
        }
        return mapper;
    }


}
