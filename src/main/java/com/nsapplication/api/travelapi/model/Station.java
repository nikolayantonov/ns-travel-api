package com.nsapplication.api.travelapi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class Station {
    private String type;
    private String plannedDateTime;
    private String plannedTrack;
    private String name;
}


