package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.TravelRequest;
import com.nsapplication.api.travelapi.model.GetTripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TravelService {
    private static Logger log = LoggerFactory.getLogger(TravelService.class);
    private static final String KEY = "a3db7e8808944380b20408e9742c86ab";
    private NsRestClient nsRestClient = new NsRestClient();

    public GetTripResponse travelHandler(TravelRequest travelRequest)
    {
        return nsRestClient.getTrips(generateURI(travelRequest), KEY);
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
