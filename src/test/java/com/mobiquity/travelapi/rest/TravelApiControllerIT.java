package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.NsResponse;
import com.mobiquity.travelapi.integrations.nsclient.responsemodel.TravelModelMapper;
import com.mobiquity.travelapi.integrations.nsclient.travelmodel.TravelPlan;
import com.mobiquity.travelapi.rest.userresponsemodels.AllRoutesResponse;
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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
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
    private TravelRequest travelRequest;

    @BeforeEach
    void setUp() {
        travelRequest = new TravelRequest.Builder()
                .dateTime("2019-10-07T16:25:00+0200")
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
    }

    @Test
    void shouldReturnAllRouteResponse() throws Exception{
        MvcResult result = mockMvc.perform (post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(travelRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo ( print())
                .andExpect ( status().isOk () )
                .andReturn ();
        String nsResultFromMock = result.getResponse ().getContentAsString ();

        assertEquals(checkTestDataResult(), nsResultFromMock);
    }

    private String checkTestDataResult() throws Exception{
        TravelPlan mockedTravelPlan = new TravelModelMapper().mapToTravelPlan(getMockedNsResponseFromFile());

        return objectMapper.writeValueAsString(new MapTravelPlanToAllRoutesResponse().mapToAllRoutesResponse ( mockedTravelPlan ));
    }

    private NsResponse getMockedNsResponseFromFile() throws Exception{
        return objectMapper.readValue(new File("./src/test/java/resources/TestNsResponse.json"), NsResponse.class);
    }

}