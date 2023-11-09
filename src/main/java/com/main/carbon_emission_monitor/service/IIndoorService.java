package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.dto.indoor.IndoorEnvironmentResponse;

public interface IIndoorService {
    IndoorEnvironmentResponse Environment(String area);

}
