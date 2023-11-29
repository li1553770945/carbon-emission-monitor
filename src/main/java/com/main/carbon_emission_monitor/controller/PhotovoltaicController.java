package com.main.carbon_emission_monitor.controller;


import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.photovoltaic.PhotovoltaicResponse;
import com.main.carbon_emission_monitor.service.IPhotovoltaicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/photovoltaic")
public class PhotovoltaicController {
    IPhotovoltaicService photovoltaicService;
    @Autowired
    PhotovoltaicController(IPhotovoltaicService photovoltaicService){
        this.photovoltaicService = photovoltaicService;
    }
    @GetMapping("/metering")
    public ResponseResult<PhotovoltaicResponse> Metering() throws BusinessException {
        PhotovoltaicResponse response = photovoltaicService.Metering();
        return ResponseResult.success(response);
    }
}