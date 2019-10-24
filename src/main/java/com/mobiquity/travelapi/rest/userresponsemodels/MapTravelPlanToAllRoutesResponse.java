package com.mobiquity.travelapi.rest.userresponsemodels;

import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


public class MapTravelPlanToAllRoutesResponse {

    @Autowired
    private WeatherClient weatherClient;

    public static AllRoutesResponse mapToAllRoutesResponse(List<RouteAndWeather> travelResponse) {
        return new MapTravelPlanToAllRoutesResponse().map(travelResponse);
    }

    private AllRoutesResponse map(List<RouteAndWeather> travelResponse) {
        return AllRoutesResponse.builder()
                .origin(travelResponse.get(0).getRoute().getOrigin().getStationName())
                .destination(travelResponse.get(0).getRoute().getDestination().getStationName())
                .availableRoutes(getRouteWeather(travelResponse))
                .build();
    }

    private List<AllRoutesResponse.RouteWeather> getRouteWeather(List<RouteAndWeather> travelResponse) {
        List<AllRoutesResponse.RouteWeather> routeWeathers = new ArrayList<>();
        for (RouteAndWeather rw : travelResponse) {
            routeWeathers.add(
                    AllRoutesResponse.RouteWeather.builder()
                            .startTime(rw.getRoute().getOrigin().getPlannedDepartureTime())
                            .weatherAtOrigin(setOriginWeather(rw)) //added for weather at start station
                            .numberOfLegs(rw.getRoute().getLegs().size())
                            .duration(rw.getRoute().getPlannedDuration())
                            .arrivalTime(rw.getRoute().getDestination().getPlannedArrivalTime())
                            .weatherAtDestination(setDestinationWeather(rw))
                            .build()
            );
        }
        return routeWeathers;
    }

    private AllRoutesResponse.RouteWeather.Weather setOriginWeather(RouteAndWeather rw) {
        return AllRoutesResponse.RouteWeather.Weather.builder()
                .summary(rw.getWeatherAtOrigin().getCurrently().getSummary())
                .humidity(rw.getWeatherAtOrigin().getCurrently().getHumidity())
                .temperature(rw.getWeatherAtOrigin().getCurrently().getTemperature())
                .windSpeed(rw.getWeatherAtOrigin().getCurrently().getWindSpeed())
                .build();
    }

    private AllRoutesResponse.RouteWeather.Weather setDestinationWeather(RouteAndWeather rw) {
        return AllRoutesResponse.RouteWeather.Weather.builder()
                .summary(rw.getWeatherAtDestination().getCurrently().getSummary())
                .humidity(rw.getWeatherAtDestination().getCurrently().getHumidity())
                .temperature(rw.getWeatherAtDestination().getCurrently().getTemperature())
                .windSpeed(rw.getWeatherAtDestination().getCurrently().getWindSpeed())
                .build();
    }

}
