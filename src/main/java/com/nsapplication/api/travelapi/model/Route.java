package com.nsapplication.api.travelapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    @JsonAlias("plannedDurationInMinutes")
    private  Integer plannedDuration;
    private   Integer transfer;
    private List<Leg> legs;
}
