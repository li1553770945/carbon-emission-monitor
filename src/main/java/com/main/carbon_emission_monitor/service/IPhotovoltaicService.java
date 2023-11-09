package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.controller.ElectricityController;
import com.main.carbon_emission_monitor.dto.photovoltaic.PhotovoltaicResponse;

public interface IPhotovoltaicService {
    PhotovoltaicResponse Metering();
}
