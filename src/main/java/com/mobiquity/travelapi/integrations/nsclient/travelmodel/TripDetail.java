package com.mobiquity.travelapi.integrations.nsclient.travelmodel;

import com.mobiquity.travelapi.integrations.weather.Weather;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
public class TripDetail {

    private String stationName;
    private String plannedDepartureTime;
    private String plannedArrivalTime;
    private String plannedTrack;
    //private Weather weather;
}
