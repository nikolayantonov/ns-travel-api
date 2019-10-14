package com.mobiquity.travelapi.integrations.stereotype;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class Route {

    private int index;
    private Itinerary origin;
    private Itinerary destination;
    private String direction;
    private int numberOfTransfers;
    private int plannedDuration;
    private List<Leg> legs;
    private BigDecimal price;


}
