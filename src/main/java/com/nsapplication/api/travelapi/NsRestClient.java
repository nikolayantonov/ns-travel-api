package com.nsapplication.api.travelapi;

import com.nsapplication.api.travelapi.model.NsTripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
class NsRestClient {
    private static Logger log = LoggerFactory.getLogger(NsRestClient.class);

    private RestTemplate restTemplate = new RestTemplate();
    private final String nsKey;
    private final String nsValue;

    NsRestClient(
            @Value("${authentication.key.name.ns}") String key,
            @Value("${authentication.key.value.ns}") String value) {
        nsKey = key;
        nsValue = value;
    }

    NsTripResponse getTrips(URI uri) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(nsKey, nsValue);
        log.info(map.keySet().toString());
        HttpHeaders httpHeaders = new HttpHeaders(map);
        HttpEntity<?> httpPackage = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<NsTripResponse> response = restTemplate.exchange(uri, HttpMethod.GET, httpPackage, NsTripResponse.class);

        log.info("GET request for Trips status :: " + response.getStatusCode().getReasonPhrase());

        return response.getBody();
    }
}
