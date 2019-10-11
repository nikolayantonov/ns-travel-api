package com.mobiquity.travelapi.integrations.nsclient;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Getter
public class NsClient {

    public String uriBase;
    private String uriPath;
    private String keyName;
    private String keyValue;
    private HttpEntity httpEntity;
//    private HttpHeaders httpHeader;

    public NsClient(@Value("${authentication.key.name.ns}") String keyName,
                    @Value("${authentication.key.value.ns}") String keyValue,
                    @Value("${urls.base.ns}") String uriBase,
                    @Value("${urls.path.ns}") String uriPath) {
        this.keyName = keyName;
        this.keyValue = keyValue;
        this.uriBase = uriBase;
        this.uriPath = uriPath;
        this.httpEntity = createHttpEntity();
    }

    private HttpHeaders createHttpHeader() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(keyName, keyValue);
        return new HttpHeaders(map);
    }

    private HttpEntity<?> createHttpEntity() {
        return new HttpEntity<>(createHttpHeader());
    }

    URI buildUri(String originEva, String destinationEva, String dateTime) {

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(uriBase).path(uriPath)
                .queryParam("originEVACode", originEva)
                .queryParam("destinationEVACode", destinationEva)
                .queryParam("dateTime", dateTime)
                .build();

        return uri.toUri();
    }
}
