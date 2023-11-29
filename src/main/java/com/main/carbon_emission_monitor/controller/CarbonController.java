package com.main.carbon_emission_monitor.controller;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.service.ICarbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carbon")
public class CarbonController {
    ICarbonService carbonService;
    @Autowired
    CarbonController(ICarbonService carbonService){
        this.carbonService = carbonService;
    }
    @GetMapping("/metering")
    public ResponseResult<MeteringResponse> Metering() throws BusinessException {
        MeteringResponse response = carbonService.Metering();
        return ResponseResult.success(response);
    }
}
