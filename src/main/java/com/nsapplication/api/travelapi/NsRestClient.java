package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.RequestInput;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class NsRestClient {

    private static final Logger log = LoggerFactory.getLogger(NsRestClient.class);
    private static final String key = "a3db7e8808944380b20408e9742c86ab";
    private static final String endPoint = "https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips?";

    //Gets Request Input and generates the URI to send to NS
    public static String getURI(RequestInput ri) {
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
        return null;
    }
}
