package com.main.carbon_emission_monitor.controller;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.service.IWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/water")
public class WaterController {
    IWaterService waterService;
    @Autowired
    WaterController(IWaterService waterService){
        this.waterService = waterService;
    }
    @GetMapping("/metering")
    public ResponseResult<MeteringResponse> Metering() throws BusinessException {
        MeteringResponse response = waterService.Metering();
        return ResponseResult.success(response);
    }
}
