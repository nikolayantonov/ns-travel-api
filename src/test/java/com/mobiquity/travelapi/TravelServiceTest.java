package com.mobiquity.travelapi;

import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class TravelServiceTest {
    @Autowired
    TravelService travelService;

    @Test
    public void checkTravelPlanWhenPassedToTravelPlanIsNotNull(){
      TravelRequest travelRequest = new TravelRequest.Builder()
              .dateTime("2019-10-07T16L25:00+0200")
              .destinationEvaCode("8400056")
              .originEvaCode("8400282")
              .build();

      assertNotNull ( travelService.getTravelPlanFromNs(travelRequest)  );

  }
}