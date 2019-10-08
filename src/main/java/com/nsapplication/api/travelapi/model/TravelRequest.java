package com.nsapplication.api.travelapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelRequest {

    private String originEVACode;
    private String destinationEVACode;
    private String dateTime;

    public TravelRequest(String originEVACode, String destinationEVACode, String dateTime) {
        this.originEVACode = originEVACode;
        this.destinationEVACode = destinationEVACode;
        this.dateTime = dateTime;
    }

//
//    public String getOriginEVACode() {
//        return originEVACode;
//    }
//
//    public void setOriginEVACode(String originEVACode) {
//        this.originEVACode = originEVACode;
//    }
//
//    public String getDestinationEVACode() {
//        return destinationEVACode;
//    }
//
//    public void setDestinationEVACode(String destinationEVACode) {
//        this.destinationEVACode = destinationEVACode;
//    }
//
//    public String getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//    }
}
