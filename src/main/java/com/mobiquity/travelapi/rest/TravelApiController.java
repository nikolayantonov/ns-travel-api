package com.mobiquity.travelapi.rest;


import com.mobiquity.travelapi.TravelService;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class TravelApiController {

//    private static Logger logger = LoggerFactory.getLogger(TravelApiController.class);
//
//    @Autowired
//    private TravelService travelService;
//
//    @RequestMapping(value = "trips", method = RequestMethod.POST, consumes = "application/json")
//    public AllRoutesResponse processUserRequest(@RequestBody TravelRequest travelRequest) {
//        logger.info("Inside controller");
//        logger.info(travelRequest.getDateTime() + "  " + travelRequest.getOriginEVACode() + "  " + travelRequest.getDestinationEVACode());
//
//        return travelService.travelHandler(travelRequest);
//    }

}
