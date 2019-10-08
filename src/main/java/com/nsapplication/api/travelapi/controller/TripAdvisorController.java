package com.nsapplication.api.travelapi.controller;


import com.nsapplication.api.travelapi.NsRestClient;
import com.nsapplication.api.travelapi.model.RequestInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller (value = "/api/")
public class TripAdvisorController {

    private static Logger log = LoggerFactory.getLogger(TripAdvisorController.class);
//
//    @PostMapping(name = "trip-request")
//    public void getAllTripsPost(@RequestBody RequestInput ri) {
//        LOGGER.info("In getAllTripsPost");
//        LOGGER.info(ri.getDateTime());
//    }

    @RequestMapping(name = "sometest", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody RequestInput addInput(@RequestBody RequestInput ri) {
        log.info("Im here");
        String tac = NsRestClient.getURI(ri);
        return ri;
    }

}
