package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.mobiquity.travelapi.integrations.travelmodel.Stop;
import lombok.Getter;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter

class NsLeg {
    @JsonAlias("name") private String trainName;
    private String direction;
    private boolean cancelled;
    @JsonAlias("origin") private NsStation startNsStation;
    @JsonAlias("destination") private NsStation endNsStation;
    private List<NsStop> stops;
    private boolean crossPlatformTransfer;
    private String travelType;
}
