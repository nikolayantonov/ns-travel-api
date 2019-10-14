package com.mobiquity.travelapi.integrations.stereotype;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Itinerary {

    private String name;
    private String plannedDepartureTime;
    private String plannedArrivalTime;
    private String plannedTrack;
    private String actualTrack;
}
