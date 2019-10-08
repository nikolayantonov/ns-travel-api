package com.nsapplication.api.travelapi.controller;

//import com.nsapplication.api.travelapi.model.TravelApi;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

import com.nsapplication.api.travelapi.model.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/")
public class TravelApiController {

    private static Logger logger = LoggerFactory.getLogger(TravelApiController.class);

//    @Autowired
//    RestTemplate restTemplate;

    // @RequestMapping("/")
    @RequestMapping(name = "trips", method = RequestMethod.POST, consumes = "application/json")
    public void processUserRequest(@RequestBody TravelRequest travelRequest) {
        logger.info("Inside controller");
        logger.info(travelRequest.getDateTime() + "  " + travelRequest.getOriginEVACode() + "  " + travelRequest.getDestinationEVACode());

    }

//    @RequestMapping("/")
//    public JsonNode sayHello(){
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("Ocp-Apim-Subscription-Key","159fd1e47462416888a7e401af0a505c");j
//        HttpEntity <String> entity = new HttpEntity<String>(headers);
//
//       // String responseBody =  restTemplate.exchange(" https://gateway.apiportal.ns.nl/public-reisinformatie/api/v2/stations", HttpMethod.GET, entity, String.class).getBody();
//
//        ResponseEntity<JsonNode> response = restTemplate.exchange("https://gateway.apiportal.ns.nl/public-reisinformatie/api/v3/trips", HttpMethod.GET, entity, JsonNode.class);
//                System.out.println(response);
//        return response.getBody();
//    }
//
//  @RequestMapping("/print")
//    public String printValues(@RequestBody TravelApi travelApi)
//  {
//      return travelApi.getFromLocation() + " " + travelApi.getToLocation();
//  }

}
