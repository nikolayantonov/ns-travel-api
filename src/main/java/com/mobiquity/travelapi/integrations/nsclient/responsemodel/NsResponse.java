package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class NsResponse {

    @JsonAlias("trips")
    private List<NsRoute> nsRoutes;

    @Getter

    @JsonIgnoreProperties(ignoreUnknown = true)
    static
    class NsRoute {
        @JsonAlias("plannedDurationInMinutes")
        private Integer plannedDuration;
        private Integer transfer;
        private List<NsLeg> legs;
        private NsProductFare productFare;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @Getter
        static
        class NsLeg {
            @JsonAlias("name") private String trainName;
            private String direction;
            private boolean cancelled;
            @JsonAlias("origin") private NsStation startNsStation;
            @JsonAlias("destination") private NsStation endNsStation;
            private List<NsStop> stops;
            private boolean crossPlatformTransfer;
            private String travelType;

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            static class NsStop {
                private String name;
            }

            @Getter
            @JsonIgnoreProperties(ignoreUnknown = true)
            static
            class NsStation {
                private String type;
                private String plannedDateTime;
                private String plannedTrack;
                private String name;
            }
        }

        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        static
        class NsProductFare {
            private int priceInCents;
        }
    }
}
