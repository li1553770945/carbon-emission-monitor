package com.main.carbon_emission_monitor.controller;


import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.indoor.IndoorEnvironmentResponse;
import com.main.carbon_emission_monitor.service.IIndoorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/indoor")
public class IndoorController {
    IIndoorService indoorService;

    @Autowired
    IndoorController(IIndoorService indoorService){
        this.indoorService = indoorService;
    }
    @GetMapping("/environment")
    public ResponseResult<IndoorEnvironmentResponse> IndoorEnvironment(@RequestParam(name = "area")   @NotNull final String area) throws BusinessException {
        IndoorEnvironmentResponse response = indoorService.Environment(area);
        return ResponseResult.success(response);
    }

}
