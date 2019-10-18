package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Leg;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Stop;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.integrations.weather.Weather;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TravelService {

    @Autowired
    private NsClient nsClient;
    @Autowired
    private WeatherClient weatherClient;

    public TravelPlan getTravelPlanFromNs(TravelRequest travelRequest) {
        return nsClient.getTravelPlan(travelRequest);
    }

    public Map<Route, Weather> getTravelPlan(TravelRequest travelRequest) {
        List<Route> routes = nsClient.getTravelPlan(travelRequest).getRoutes();
        for (Route route: routes) {
            Stop lastStopOfRoute = getLastStop(route);
            weatherClient.getDarkSkyResponse(getLongitude(lastStopOfRoute), getLatitude(lastStopOfRoute), getDateTime(route));
        }
        return null;
    }

    private String getDateTime(Route route) {
        return getEpochTime(route.getOrigin().getPlannedArrivalTime());
    }

    private String getLongitude(Stop lastStop) {
        return lastStop.getLongitude();
    }

    private String getLatitude(Stop lastStop) {
        return lastStop.getLatitude();
    }

    private Stop getLastStop(Route route) {
        Leg lastLeg = getLastLeg(route);
        return lastLeg.getStops().get(lastLeg.getStops().size() - 1);
    }

    private Leg getLastLeg(Route route) {
        return route.getLegs().get(route.getLegs().size() - 1);
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
