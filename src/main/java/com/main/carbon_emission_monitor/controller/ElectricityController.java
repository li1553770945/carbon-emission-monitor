package com.main.carbon_emission_monitor.controller;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.service.IElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/electricity")
public class ElectricityController {
    IElectricityService electricityService;
    @Autowired
    ElectricityController(IElectricityService electricityService){
        this.electricityService = electricityService;
    }
    @GetMapping("/metering")
    public ResponseResult<MeteringResponse> Metering() throws BusinessException {
        MeteringResponse response = electricityService.Metering();
        return ResponseResult.success(response);
    }
}
