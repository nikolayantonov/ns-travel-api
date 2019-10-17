package com.mobiquity.travelapi.integrations.weather;

import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class WeatherClient {

  @Autowired
    RestTemplate restTemplate;
    private String uriBase;
    private HttpEntity httpEntity;
    private String key;
    public WeatherClient(@Value("${authentication.key.darkSky}") String key,
                  @Value("${urls.base.ns}") String uriBase) {
    }


    public ResponseEntity<Weather> getDarkSkyResponse(TravelPlan travelPlan) {
        return restTemplate.exchange(buildUri(travelPlan), HttpMethod.GET, httpEntity, Weather.class);
    }


    URI buildUri(TravelPlan travelPlan) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(uriBase)
                .queryParam(key)
                .queryParam( travelPlan.getRoutes().get(0).getLegs().get(0).getStops().get(0).getLatitude())
                .queryParam( travelPlan.getRoutes().get(0).getLegs().get(0).getStops().get(0).getLongitude())
                .queryParam(travelPlan.getRoutes().get(0).getOrigin().getPlannedDepartureTime())
                .build();

        return uri.toUri();
    }

}
