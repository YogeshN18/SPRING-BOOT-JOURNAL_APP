package com.pratice.JournalApp_SpringBoot_Project.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {

    @GetMapping("health-check")
    public String healthCheck()
    {
       log.info("Health check endpoint was called successfully.");
           return "Every thing working fine and our Spring app Started";

    }
}
