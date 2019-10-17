package com.mobiquity.travelapi;

<<<<<<< HEAD
<<<<<<< HEAD
import com.mobiquity.travelapi.integrations.travelmodel.TravelPlan;
import com.nsapplication.api.travelapi.model.TravelRequest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
=======
import org.junit.jupiter.api.Test;
>>>>>>> 51e21754a3fce8c7d5ff3cd02d678c18ad3afdcf
=======
import com.mobiquity.travelapi.integrations.nsclient.NsClient;
import com.mobiquity.travelapi.rest.userresponsemodels.TravelRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
>>>>>>> 6c87aaa5e50be3e9fe769458b743d67372046fd6

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class TravelServiceTest {
    @Autowired
    private TravelService travelService;

    @Test
    public void checkTravelPlanWhenPassedToTravelPlanIsNotNull(){
      TravelRequest travelRequest = new TravelRequest.Builder()
              .dateTime("2019-10-07T16L25:00+0200")
              .destinationEvaCode("8400056")
              .originEvaCode("8400282")
              .build();

      assertNotNull ( travelService.getTravelPlanFromNs(travelRequest)  );
  }

<<<<<<< HEAD


=======
>>>>>>> 51e21754a3fce8c7d5ff3cd02d678c18ad3afdcf
}