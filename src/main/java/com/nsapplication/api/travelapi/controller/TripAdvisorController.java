package com.nsapplication.api.travelapi.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TripAdvisorController {

    private static Logger log = LoggerFactory.getLogger(TripAdvisorController.class);

    @GetMapping(value = "/thisplace")
    public String returnWorld() {
        log.info("Here");
        return "Hello World";
    }

    @GetMapping(value = "/here")
    public ModelAndView helloWorld() {
        log.info("This place");
        return new ModelAndView("welcome", "message", new HiThere());
    }

}
