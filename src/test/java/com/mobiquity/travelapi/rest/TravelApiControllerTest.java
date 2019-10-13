package com.mobiquity.travelapi.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class TravelApiControllerTest {

    @Autowired
    private TravelApiController controller;

    @Test
    public void contextLoads() {
        assertNotNull(controller);
        //Autowire RestTemplate in here
        //Also need to add a port variable (global variable) with:
                // @LocalServerPort
                // private int port
        //Before this test is done, the Request Object will need to be built for this to pass
        //Is this an integration test? If not, then how does one do it without initializing spring
    }

}