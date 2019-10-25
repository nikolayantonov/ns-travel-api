package com.mobiquity.travelapi.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class HealthCheckController {

    @GetMapping
    public @ResponseBody String getHealth() {
        return "You think this is air?";
    }
}
