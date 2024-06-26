package com.main.carbon_emission_monitor.dto.indoor;

import lombok.Data;

@Data
public class IndoorEnvironmentResponse {
    private double humidity;
    private double PM25;
    private double temperature;
    private double CO2;
}
