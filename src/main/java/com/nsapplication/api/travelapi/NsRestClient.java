package com.nsapplication.api.travelapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class NsRestClient {

    private static final Logger log = LoggerFactory.getLogger(NsRestClient.class);
    private static final String key = "a3db7e8808944380b20408e9742c86ab";
    private static final String endPoint = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?";

    //Gets Request Input and generates the URI to send to NS
//    public static String getURI(RequestInput ri) {
//        StringBuilder uriBuilder = new StringBuilder();
//        uriBuilder.append(endPoint);
//
//        uriBuilder.append(ri.getOriginEVACode());
//        uriBuilder.append("&");
//        uriBuilder.append(ri.getDestinationEVACode());
//        uriBuilder.append("&");
//        uriBuilder.append(ri.getDateTime());
//
//        System.out.println(uriBuilder.toString());
//
//        HttpClient client = HttpClient.newBuilder().build();
//
//        HttpRequest request = HttpRequest.newBuilder(URI.create(uriBuilder.toString()))
//                .headers("Ocp-Apim-Subscription-Key", key, "Accept", "application/json", "Content-Type", "application/json")
//                .build();
//
//        try {
//            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body().toString());
//        } catch (Exception e) {
//            e.getLocalizedMessage();
//        }
//        return null;
//    }

    public void getNsTrips(String url, String keyValue) {

        //Add headers
        //Do request
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Ocp-Apim-Subscription-Key", keyValue);

        HttpHeaders headers = new HttpHeaders(map);
        HttpEntity<?> entity = new HttpEntity<Object>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        headers.add("Ocp-Apim-Subscription-Key", keyValue);
//        headers.add("Accept", "applicaton/json");
//        headers.add("Content-Type", "applciation/json");


        log.info(response.toString());


    }
}
