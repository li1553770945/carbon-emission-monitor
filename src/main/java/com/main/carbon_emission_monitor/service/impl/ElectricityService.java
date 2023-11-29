package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import com.main.carbon_emission_monitor.service.IElectricityService;
import com.main.carbon_emission_monitor.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ElectricityService implements IElectricityService {


    private final IDeviceDataService deviceDataService;


    @Autowired
    public ElectricityService(IDeviceDataService deviceDataService){
        this.deviceDataService = deviceDataService;
    }
    public MeteringResponse Metering(){
        MeteringResponse response = new MeteringResponse();

        // 生成每天、每周、每月、每年的能耗数据
        response.setDailyData(deviceDataService.getDailyData(StatisticTypeEnum.Electricity,Calendar.getInstance()));
        response.setWeeklyData(deviceDataService.getWeeklyData(StatisticTypeEnum.Electricity,Calendar.getInstance()));
        response.setMonthlyData(deviceDataService.getMonthlyData(StatisticTypeEnum.Electricity,Calendar.getInstance()));
        response.setYearlyData(deviceDataService.getYearlyData(StatisticTypeEnum.Electricity,Calendar.getInstance()));

        // 生成最近7天的用电量和费用数据
        response.setConsumptionData(deviceDataService.getLastNDayData(StatisticTypeEnum.Electricity,Calendar.getInstance(),10));
        return response;
    }

}
