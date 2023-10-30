package com.main.carbon_emission_monitor.controller;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ErrorCodeEnums;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.dto.main.*;
import com.main.carbon_emission_monitor.service.MainService;
import com.main.carbon_emission_monitor.service.impl.MainServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/main")
public class MainController {

    MainService mainService;
    @Autowired
    MainController(MainServiceImpl mainService){
        this.mainService = mainService;
    }
    @GetMapping("/photovoltaic-parameter")
    public ResponseResult<PhotovoltaicParameterResponse> photovoltaicParameter() throws BusinessException {
        PhotovoltaicParameterResponse response = mainService.PhotovoltaicParameter();
        return ResponseResult.success(response);
    }
    @GetMapping("/electricity-metering")
    public ResponseResult<ElectricityMeteringResponse> ElectricityMetering() throws BusinessException {
        ElectricityMeteringResponse response = mainService.ElectricityMetering();
        return ResponseResult.success(response);
    }
    @GetMapping("/water-metering")
    public ResponseResult<WaterMeteringResponse> WaterMetering() throws BusinessException {
        WaterMeteringResponse response = mainService.WaterMetering();
        return ResponseResult.success(response);
    }
    @GetMapping("/carbon-metering")
    public ResponseResult<CarbonMeteringResponse> CarbonMetering() throws BusinessException {
        CarbonMeteringResponse response = mainService.CarbonMetering();
        return ResponseResult.success(response);
    }
    @GetMapping("/indoor-environment")
    public ResponseResult<IndoorEnvironmentResponse> IndoorEnvironment(@RequestParam(name = "area")   @NotNull final String area) throws BusinessException {
        IndoorEnvironmentResponse response = mainService.IndoorEnvironment(area);
        return ResponseResult.success(response);
    }
    @GetMapping("/today")
    public ResponseResult<TodayResponse> Today() throws BusinessException {
        TodayResponse response = mainService.Today();
        return ResponseResult.success(response);
    }

}
