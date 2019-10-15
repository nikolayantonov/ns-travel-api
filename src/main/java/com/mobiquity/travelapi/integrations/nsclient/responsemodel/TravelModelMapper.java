package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.mobiquity.travelapi.integrations.travelmodel.*;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TravelModelMapper {

    public TravelPlan mapToTravelPlan(NsResponse nsResponse) {
        return TravelPlan.builder()
                .routes(getListOfRoutes(nsResponse))
                .build();
    }

    private List<Route> getListOfRoutes(NsResponse nsResponse) {
//        List<Route> stRoutes = new ArrayList<>();
//
//        nsResponse.getNsRoutes().forEach(
//                nsRoute -> stRoutes.add(buildRoute(nsRoute))
//        );

        return new ArrayList<Route>() {{
           nsResponse.getNsRoutes().forEach(
                   nsRoute -> add(buildRoute(nsRoute))
           );
        }};
//        return stRoutes;
    }

    private List<Leg> getListOfLegs(List<NsLeg> nsLegs) {
        List<Leg> listOfRouteLegs = new ArrayList<>();

        nsLegs.forEach(
                nsLeg -> listOfRouteLegs.add(buildLeg(nsLeg) )
        );
        return listOfRouteLegs;
    }

    private List<Stop> getNsStops(List<NsStop> nsStops) {
       List<Stop> stops = new ArrayList<>();
       nsStops.forEach(
               nsStop -> stops.add(buildStop(nsStop))
       );
       return stops;
    }

    private Route buildRoute(NsRoute nsRoute) {
        return Route.builder()
                .origin( getTripDetailForOrigin( nsRoute) )
                .destination( getTripDetailForDestination( nsRoute) )
                .plannedDuration( nsRoute.getPlannedDuration() )
                .numberOfTransfers( nsRoute.getLegs().size() - 1 )
                .legs( getListOfLegs( nsRoute.getLegs()) )
                .price( nsRoute.getProductFare().getPriceInCents() )
                .build();
    }

    private Leg buildLeg(NsLeg nsLeg) {
        return Leg.builder()
                .arrivalPlatform( nsLeg.getEndNsStation().getPlannedTrack() )
                .departurePlatform( nsLeg.getStartNsStation().getPlannedTrack() )
                .endStation( nsLeg.getStops().get( nsLeg.getStops().size() -1 ).getName() )
                .numberOfStops( nsLeg.getStops().size() )
                .startStation( nsLeg.getStartNsStation().getName() )
                .stops( getNsStops( nsLeg.getStops() ) )
                .travelType( nsLeg.getTravelType() )
                .build();
    }

    private Stop buildStop(NsStop nsStop) {
        return Stop.builder()
                .name(nsStop.getName())
                .build();
    }

    private TripDetail getTripDetailForOrigin(NsRoute nsRoute) {
        NsLeg firstLeg = getNsLegFromRoute(nsRoute, 0);
        return TripDetail.builder()
                .stationName( firstLeg.getStartNsStation().getName() )
                .plannedDepartureTime( firstLeg.getStartNsStation().getPlannedDateTime() )
                .plannedTrack( firstLeg.getStartNsStation().getPlannedTrack() )
                .plannedArrivalTime( firstLeg.getEndNsStation().getPlannedDateTime())
                .build();
    }

    private TripDetail getTripDetailForDestination(NsRoute nsRoute) {
        NsLeg lastLeg = getNsLegFromRoute(nsRoute, nsRoute.getLegs().size() - 1);
        return TripDetail.builder()
                .stationName( lastLeg.getStops().get(lastLeg.getStops().size() - 1) .getName())
                .plannedDepartureTime( lastLeg.getEndNsStation().getPlannedDateTime() )
                .plannedTrack( lastLeg.getEndNsStation().getPlannedTrack() )
                .plannedArrivalTime( lastLeg.getEndNsStation().getPlannedDateTime() )
                .build();
    }

    private NsLeg getNsLegFromRoute(NsRoute nsRoute, int legLocation) {
        return nsRoute.getLegs().get(legLocation);
    }

}
