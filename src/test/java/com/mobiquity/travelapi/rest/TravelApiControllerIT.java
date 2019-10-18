package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.dto.RouteAndWeather;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.Route;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelApiControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WeatherClient weatherClient;
    private TravelRequest travelRequest;
    private String longitude, latitude;
    private List<String> dateTime;


    @BeforeEach
    void setUp() {
        travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16:25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
        latitude = "52.337301";
        longitude = "4.889512";
        dateTime = List.of("1570458540", "1570461060", "1570460340", "1570462860", "1570462140", "1570464660");
    }

    @Test
    void shouldReturnAllRouteResponse() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(travelRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String nsResultFromMock = result.getResponse().getContentAsString();

        assertEquals(checkTestDataResult(), nsResultFromMock);
    }

    private String checkTestDataResult() throws Exception {
        TravelPlan mockedTravelPlan = TravelModelMapper.mapToTravelPlan(getMockedNsResponseFromFile());
        List<RouteAndWeather> mockedTravelResponse = new ArrayList<>();
        int counter = 0;

        for (Route route: mockedTravelPlan.getRoutes()) {
            mockedTravelResponse.add(RouteAndWeather.builder()
                    .route(route)
                    .weather(weatherClient.getDarkSkyResponse(
                            longitude,
                            latitude,
                            dateTime.get(counter++)).getBody()
                    )
                    .build());
        }

        return objectMapper.writeValueAsString(MapTravelPlanToAllRoutesResponse.mapToAllRoutesResponse(mockedTravelResponse));
    }

    private NsResponse getMockedNsResponseFromFile() throws Exception {
        return objectMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"), NsResponse.class);
    }

}