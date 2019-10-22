package com.mobiquity.travelapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.travelapi.integrations.weather.WeatherClient;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.NestedServletException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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


    @BeforeEach
    void setUp() {
        travelRequest = new TravelRequest.Builder()
                .dateTime(getCurrentDateTime())
                .destinationEvaCode("8400056")
                .originEvaCode("8400282")
                .build();
        latitude = "52.337301";
        longitude = "4.889512";
    }

    private String getCurrentDateTime() {
        ZonedDateTime aDate = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ddZ");
        return aDate.format(formatter);
    }

    @Test
    void statusCode_400_expectedFromNs_IfNothingPassedToTravelService() throws Exception {
        mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is(400));
    }

    @Test
    void statusCode_200_ifCorrectPackageSentToNs() throws Exception {
        mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(travelRequest)))
                .andExpect(status().is(200));
    }

    @Test
    void routeAndWeatherSize_IsNotNull_ifCorrectPackageSentToNs() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(travelRequest)))
                .andExpect(status().is(200))
                .andReturn();

        System.out.println("Stop");
    }

    @Test
    void routeAndWeather_IsNull_ifNothingSentToNs() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is(400))
                .andReturn();

        System.out.println("Stop");
    }

    @Test
    void statusCode_415_ifIncorrectKeyUsed() {

//        try {
//            mockMvc.perform(post("/api/v1/trips")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(travelRequest)));
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        NestedServletException thrown =
                assertThrows(NestedServletException.class,
                () -> mockMvc.perform(post("/api/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(travelRequest))),
                        "Expected servlet exception but didn't occur");
//        thrown.getCause()
        Throwable throwable = thrown.getRootCause().getCause();
        assertTrue(thrown.getMessage().contains("Request processing failed"));
    }

    void shouldReturnAllRouteResponse() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(travelRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String nsResultFromMock = result.getResponse().getContentAsString();

    }


}