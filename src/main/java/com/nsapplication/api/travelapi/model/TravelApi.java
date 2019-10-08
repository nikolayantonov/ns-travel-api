package com.nsapplication.api.travelapi.model;


//import org.springframework.web.bind.annotation.Mapping;

public class TravelApi {

//   private  String date;
//   private String time;
   private String fromLocation;
   private String toLocation;

//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public TravelApi() {
    }

    public TravelApi( String fromLocation, String toLocation) {
//        this.date = date;
//        this.time = time;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

//    public String getDate() {
//        return date;
//    }
}
