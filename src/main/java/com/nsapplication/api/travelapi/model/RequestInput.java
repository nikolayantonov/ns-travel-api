package com.nsapplication.api.travelapi.model;


import lombok.Getter;
//import lombok.Setter;

@Getter
//@Setter
public class RequestInput {
    private String originEVACode;
    private String destinationEVACode;
    private String dateTime;

    public RequestInput() {
    }

    public String getOriginEVACode() {
        return "originEVACode=" + originEVACode;
    }

    public String getDestinationEVACode() {
        return "destinationEVACode=" + destinationEVACode;
    }

    public String getDateTime() {
        return "dateTime=" + dateTime;
    }
}
