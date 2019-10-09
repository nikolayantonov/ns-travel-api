package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.mapper.MapToRoutesViewFromTripResponse;
import com.nsapplication.api.travelapi.model.NsTripResponse;
import com.nsapplication.api.travelapi.model.TravelRequest;
import com.nsapplication.api.travelapi.view.RoutesView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TravelService {
    private static Logger log = LoggerFactory.getLogger(TravelService.class);
    private static final String KEY = "a3db7e8808944380b20408e9742c86ab";
    private NsRestClient nsRestClient = new NsRestClient();
    MapToRoutesViewFromTripResponse mapToRoutesViewFromTripResponse = new MapToRoutesViewFromTripResponse ();

    public RoutesView travelHandler(TravelRequest travelRequest)
    {   // nsRestClient.getTrips(generateURI(travelRequest), KEY); --> NsTripResponse
        // Take that response ---> CONVERT ---> RoutesView

        NsTripResponse nsTripResponse = nsRestClient.getTrips(generateURI(travelRequest), KEY);
        log.info ( "About to map to routesView" );
        return mapToRoutesViewFromTripResponse.map ( nsTripResponse );
    }


    URI generateURI(TravelRequest travelRequest) {

        String baseUri = "gateway.apiportal.ns.nl/public-reisinformatie/api/v3";
        String path = "trips";

        UriComponents uriPath = UriComponentsBuilder.newInstance()
                .scheme("https").host(baseUri).path(path)
                .queryParam("originEVACode", travelRequest.getOriginEVACode())
                .queryParam("destinationEVACode", travelRequest.getDestinationEVACode())
                .queryParam("dateTime",travelRequest.getDateTime())
                .build();

        return uriPath.toUri();
    }

}
