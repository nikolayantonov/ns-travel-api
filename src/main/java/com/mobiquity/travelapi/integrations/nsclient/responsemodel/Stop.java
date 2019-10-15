package com.mobiquity.travelapi.integrations.nsclient.responsemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
class Stop {
   private String name;

}
