package com.nsapplication.api.travelapi.controller;

import com.nsapplication.api.travelapi.TravelService;
import com.nsapplication.api.travelapi.model.TravelRequest;
import com.nsapplication.api.travelapi.view.RoutesView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class TravelApiController {

    private static Logger logger = LoggerFactory.getLogger(TravelApiController.class);
    @Autowired
    private TravelService travelService;

    @RequestMapping(value = "trips", method = RequestMethod.POST, consumes = "application/json")
    public RoutesView processUserRequest(@RequestBody TravelRequest travelRequest) {
        logger.info("Inside controller");
        logger.info(travelRequest.getDateTime() + "  " + travelRequest.getOriginEVACode() + "  " + travelRequest.getDestinationEVACode());

        return travelService.travelHandler(travelRequest);
    }

}
