package com.microservices.limitsservices.controllers;

import com.microservices.limitsservices.beans.LimitConfiguration;
import com.microservices.limitsservices.configs.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
        return new LimitConfiguration(configuration.getMax(), configuration.getMin());
    }

}
