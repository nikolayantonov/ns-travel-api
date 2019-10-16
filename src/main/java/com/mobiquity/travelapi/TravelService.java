package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelService {

    @Autowired
    private NsClient nsClient;

    public TravelPlan getTravelPlanFromNs(TravelRequest travelRequest) {
        return nsClient.getTravelPlan(travelRequest);
    }
}
