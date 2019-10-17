package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class  TravelApiControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAllRouteResponse()  throws Exception{

        //assert : AllRoutesResponse == Controller response
       // AllRoutesResponse allRoutesResponse ;

        TravelRequest travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16L25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();

        MvcResult result = mockMvc.perform (post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content (asJsonString(travelRequest)))
                .andReturn();


        String returnObject = result.getResponse ().getContentAsString ();

        //mocks AllRoutesResponse
        ObjectMapper objectMapper = new ObjectMapper (  );
       // NsResponse fakeNsResponse = objectMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"),NsResponse.class);
        AllRoutesResponse fakeAllRoutesResponse = objectMapper.readValue(new File("./src/test/java/resources/TestAllRoutesResponse.json"), AllRoutesResponse.class);
//        TravelModelMapper mapToTravelPlan = new TravelModelMapper ();
//        TravelPlan fakeTravelPlan = mapToTravelPlan.mapToTravelPlan ( fakeNsResponse );
//        MapTravelPlanToAllRoutesResponse mapToExpectedResponse = new MapTravelPlanToAllRoutesResponse ();
//        AllRoutesResponse fakeAllRoutesResponse = mapToExpectedResponse.mapToAllRoutesResponse ( fakeTravelPlan );
//        System.out.println(returnObject);

        System.out.println(asJsonString(fakeAllRoutesResponse));
        assertTrue ( returnObject.equals(asJsonString(fakeAllRoutesResponse)) );

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}