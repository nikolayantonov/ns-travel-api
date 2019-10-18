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

import java.time.ZonedDateTime;

@Service
public class TravelService {

    @Autowired
    private NsClient nsClient;

    public TravelPlan getTravelPlanFromNs(TravelRequest travelRequest) {
        return nsClient.getTravelPlan(travelRequest);
    }

    //Get weather by sending locations + epoch

    String getEpochTime(String dateTime) {
        ZonedDateTime zdt = ZonedDateTime.parse(correctDateTime(dateTime));
        return String.valueOf(zdt.toEpochSecond());
    }

    private String correctDateTime(String dateTime) {
        StringBuilder sb = new StringBuilder().append(dateTime);
        sb.delete(sb.indexOf("+") + 3, sb.length());
        return sb.append(":00").toString();
    }
}
