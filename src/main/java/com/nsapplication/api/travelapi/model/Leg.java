package com.nsapplication.api.travelapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
//@Setter

public class Leg {
    @JsonAlias("name") private String trainName;
    private String direction;
    private boolean cancelled;
    @JsonAlias("origin") private Station startStation;
    @JsonAlias("destination") private Station endStation;
    private List<Stop> stops;
    private boolean crossPlatformTransfer;

}
