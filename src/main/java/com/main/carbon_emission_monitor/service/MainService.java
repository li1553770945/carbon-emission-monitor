package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.dto.main.*;

public interface MainService {
    PhotovoltaicParameterResponse PhotovoltaicParameter();
    ElectricityMeteringResponse ElectricityMetering();

    WaterMeteringResponse WaterMetering();
    CarbonMeteringResponse CarbonMetering();
    TodayResponse Today();
    IndoorEnvironmentResponse IndoorEnvironment(String area);



}
