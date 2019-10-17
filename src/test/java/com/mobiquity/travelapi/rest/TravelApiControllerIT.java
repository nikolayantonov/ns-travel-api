package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TravelApiControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllRouteResponse()  throws Exception{

        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16:25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        String jsonString = objectMapper.writeValueAsString(travelRequest);

        MvcResult result = mockMvc.perform (post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andDo ( print())
                .andExpect ( status().isOk () )
                .andReturn ();

        String returnObject = result.getResponse ().getContentAsString ();

        //mocks AllRoutesResponse
        NsResponse fakeNsResponse = objectMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"), NsResponse.class);
        TravelModelMapper mapToTravelPlan = new TravelModelMapper ();
        TravelPlan fakeTravelPlan = mapToTravelPlan.mapToTravelPlan ( fakeNsResponse );
        MapTravelPlanToAllRoutesResponse mapToExpectedResponse = new MapTravelPlanToAllRoutesResponse ();
        AllRoutesResponse fakeAllRoutesResponse = mapToExpectedResponse.mapToAllRoutesResponse ( fakeTravelPlan );

        assertEquals ( returnObject, objectMapper.writeValueAsString(fakeAllRoutesResponse) );
    }

}