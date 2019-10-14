package com.nsapplication.api.travelapi.mapper;

import com.nsapplication.api.travelapi.model.NsTripResponse;
import com.nsapplication.api.travelapi.model.Route;
import com.nsapplication.api.travelapi.view.RouteView;
import com.nsapplication.api.travelapi.view.RoutesView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapToRoutesViewFromTripResponse {
    private static Logger log = LoggerFactory.getLogger ( MapToRoutesViewFromTripResponse.class );
    private RoutesView routesView = new RoutesView ();


    public RoutesView map(NsTripResponse nsTripResponse) {
        log.info ( "Attempting to map " + nsTripResponse.getClass ().getCanonicalName () );
        routesView.setRouteList ( getListOfRoutes(nsTripResponse.getRoutes ()) );
        routesView.setStartStation ( nsTripResponse.getRoutes().get ( 0 ).getLegs ().get ( 0 ).getStartStation ().getName () );
        routesView.setEndStation ( nsTripResponse.getRoutes ().get ( 0 ).getLegs ().get ( nsTripResponse.getRoutes().get( 0 ).getLegs().size () -1 ).getEndStation ().getName () );
        return this.routesView;
    }

    private List<RouteView> getListOfRoutes(List<Route> nsRoutes) {
        List<RouteView> ourRoutes = new ArrayList<> ( );
        RouteView oneRoute = new RouteView ();

        for (Route nsRoute: nsRoutes) {
            oneRoute.setDuration ( nsRoute.getPlannedDuration () );
            ourRoutes.add ( oneRoute );
        }

        return ourRoutes;
    }

}
