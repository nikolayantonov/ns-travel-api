package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TravelService {

    Logger log = LoggerFactory.getLogger(TravelService.class);
    @Autowired
    NsClient nsClient;

    MapTravelPlanToAllRoutesResponse mapTravelPlanToAllRoutesResponse =new MapTravelPlanToAllRoutesResponse();
    public AllRoutesResponse travelHandler(TravelRequest travelRequest) {
        TravelPlan travelPlan = nsClient.getTravelPlan(travelRequest);// have to check
        log.info ( "Mapping travel plan to All RoutesResponse" );
        return mapTravelPlanToAllRoutesResponse.mapToAllRoutesResponse(travelPlan);

    }
}
