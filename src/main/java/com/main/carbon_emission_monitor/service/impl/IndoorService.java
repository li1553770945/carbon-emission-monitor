package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.dto.indoor.IndoorEnvironmentResponse;
import com.main.carbon_emission_monitor.service.IIndoorService;
import org.springframework.stereotype.Service;

@Service
public class IndoorService implements IIndoorService {
    public IndoorEnvironmentResponse Environment(String area){
        IndoorEnvironmentResponse response = new IndoorEnvironmentResponse();

        response.setHumidity(Math.random() * 100); // Modify the range as needed.
        response.setPM25(Math.random() * 100); // Modify the range as needed.
        response.setTemperature(Math.random() * 30); // Modify the range as needed.
        response.setCO2(Math.random() * 1000); // Modify the range as needed.

        return response;
    }
}
