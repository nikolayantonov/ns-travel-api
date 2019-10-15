package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.mobiquity.travelapi.integrations.travelmodel.*;
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
        return new ArrayList<Route>() {{
            nsResponse.getNsRoutes().forEach(
                   nsRoute -> add(buildRoute(nsRoute))
            );
        }};
    }

    private List<Leg> getListOfLegs(List<NsResponse.NsRoute.NsLeg> nsLegs) {
        return new ArrayList<Leg>() {{
            nsLegs.forEach(
                    nsLeg -> add(buildLeg(nsLeg))
            );
        }};
    }

    private List<Stop> getNsStops(List<NsResponse.NsRoute.NsLeg.NsStop> nsStops) {
        return new ArrayList<Stop>() {{
            nsStops.forEach(
                    nsStop -> add(buildStop(nsStop))
            );
        }};
    }

    private Route buildRoute(NsResponse.NsRoute nsRoute) {
        return Route.builder()
                .origin( getTripDetailForOrigin( nsRoute) )
                .destination( getTripDetailForDestination( nsRoute) )
                .plannedDuration( nsRoute.getPlannedDuration() )
                .numberOfTransfers( nsRoute.getLegs().size() - 1 )
                .legs( getListOfLegs( nsRoute.getLegs()) )
                .price( nsRoute.getProductFare().getPriceInCents() )
                .build();
    }

    private Leg buildLeg(NsResponse.NsRoute.NsLeg nsLeg) {
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

    private Stop buildStop(NsResponse.NsRoute.NsLeg.NsStop nsStop) {
        return Stop.builder()
                .name(nsStop.getName())
                .build();
    }

    private TripDetail getTripDetailForOrigin(NsResponse.NsRoute nsRoute) {
        NsResponse.NsRoute.NsLeg firstLeg = getNsLegFromRoute(nsRoute, 0);
        return TripDetail.builder()
                .stationName( firstLeg.getStartNsStation().getName() )
                .plannedDepartureTime( firstLeg.getStartNsStation().getPlannedDateTime() )
                .plannedTrack( firstLeg.getStartNsStation().getPlannedTrack() )
                .plannedArrivalTime( firstLeg.getEndNsStation().getPlannedDateTime())
                .build();
    }

    private TripDetail getTripDetailForDestination(NsResponse.NsRoute nsRoute) {
        NsResponse.NsRoute.NsLeg lastLeg = getNsLegFromRoute(nsRoute, nsRoute.getLegs().size() - 1);
        return TripDetail.builder()
                .stationName( lastLeg.getStops().get(lastLeg.getStops().size() - 1) .getName())
                .plannedDepartureTime( lastLeg.getEndNsStation().getPlannedDateTime() )
                .plannedTrack( lastLeg.getEndNsStation().getPlannedTrack() )
                .plannedArrivalTime( lastLeg.getEndNsStation().getPlannedDateTime() )
                .build();
    }

    private NsResponse.NsRoute.NsLeg getNsLegFromRoute(NsResponse.NsRoute nsRoute, int legLocation) {
        return nsRoute.getLegs().get(legLocation);
    }

}
