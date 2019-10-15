package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

import java.util.List;

@Getter

@JsonIgnoreProperties(ignoreUnknown = true)
class NsRoute {
    @JsonAlias("plannedDurationInMinutes")
    private Integer plannedDuration;
    private Integer transfer;
    private List<NsLeg> legs;
    private NsProductFare productFare;

}
