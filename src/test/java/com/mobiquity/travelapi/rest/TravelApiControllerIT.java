package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
import com.mobiquity.travelapi.rest.userresponsemodels.MapTravelPlanToAllRoutesResponse;
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

    @Test
    public void shouldReturnAllRouteResponse()  throws Exception{





        //assert : AllRoutesResponse == Controller response
       // AllRoutesResponse allRoutesResponse ;


        //Controller response --> string
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<> (  );
            valueMap.add("datetime", "2019-10-07T16L25:00+0200");
            valueMap.add ( "destinationEvaCode", "8400056" );
            valueMap.add ( "originEvaCode", "8400282" );
            JSONObject json = new JSONObject ( valueMap );


        MvcResult result = mockMvc.perform (post("/api/v1/trips")
//                .content ( json.toString () ).contentType ( MediaType.APPLICATION_JSON_UTF8 ).

                .andDo ( print())
                .andExpect ( status().isOk () )
                .andReturn ();

        String returnObject = result.getResponse ().getContentAsString ();

        //mocks AllRoutesResponse
        ObjectMapper objectMapper = new ObjectMapper (  );
        NsResponse fakeNsResponse = objectMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"), NsResponse.class);
        TravelModelMapper mapToTravelPlan = new TravelModelMapper ();
        TravelPlan fakeTravelPlan = mapToTravelPlan.mapToTravelPlan ( fakeNsResponse );
        MapTravelPlanToAllRoutesResponse mapToExpectedResponse = new MapTravelPlanToAllRoutesResponse ();
        AllRoutesResponse fakeAllRoutesResponse = mapToExpectedResponse.mapToAllRoutesResponse ( fakeTravelPlan );

        assertEquals ( returnObject, fakeAllRoutesResponse.toString () );






    }

}