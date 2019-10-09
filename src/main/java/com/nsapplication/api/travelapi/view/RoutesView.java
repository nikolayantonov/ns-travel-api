package com.nsapplication.api.travelapi.view;

import com.nsapplication.api.travelapi.model.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoutesView {
    private String startStation;
    private String endStation;
    private List<RouteView> routeList;

}
