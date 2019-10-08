package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.TravelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

@Service
public class TravelService {

    @Autowired
    RestTemplate restTemplate;

    public void  travelHandler(TravelRequest travelRequest)
    {

    }

    public URI generateURI(TravelRequest travelRequest) {

        String baseUri = "gateway.apiportal.ns.nl/public-reisinformatie/api/v3";
        String path = "trips";
        //URI uri = UriBuilder().path(baseUri).queryParam()

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Ocp-Apim-Subscription-Key","159fd1e47462416888a7e401af0a505c");


        UriComponents uriPath = UriComponentsBuilder.newInstance()
                .scheme("https").host(baseUri).path(path)
                .queryParam("originEVACode", travelRequest.getOriginEVACode())
                .queryParam("destinationEVACode", travelRequest.getDestinationEVACode())
                .queryParam("dateTime",travelRequest.getDateTime())
                .build();
//        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUri)
//                .queryParam("origincode", travelRequest.getOriginEVACode())
//                .queryParam("destinationcode", travelRequest.getDestinationEVACode())
//                .queryParam("datetime", travelRequest.getDateTime());
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        HttpEntity<String> response = restTemplate.exchange(
//                builder.toUriString(),
//                HttpMethod.GET,
//                entity,
//                String.class);
//        System.out.println(response);
        return uriPath.toUri();
    }
}
