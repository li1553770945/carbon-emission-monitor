package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;
import com.main.carbon_emission_monitor.service.IDeviceDataService;
import com.main.carbon_emission_monitor.service.IWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
public class WaterService implements IWaterService {

    private final IDeviceDataService deviceDataService;


    @Autowired
    public WaterService(IDeviceDataService deviceDataService){
        this.deviceDataService = deviceDataService;
    }
    public MeteringResponse Metering() {
        MeteringResponse response = new MeteringResponse();

        // 生成每天、每周、每月、每年的能耗数据
        response.setDailyData(deviceDataService.getDailyData(StatisticTypeEnum.Water, Calendar.getInstance()));
        response.setWeeklyData(deviceDataService.getWeeklyData(StatisticTypeEnum.Water, Calendar.getInstance()));
        response.setMonthlyData(deviceDataService.getMonthlyData(StatisticTypeEnum.Water, Calendar.getInstance()));
        response.setYearlyData(deviceDataService.getYearlyData(StatisticTypeEnum.Water, Calendar.getInstance()));

        // 生成最近7天的用水量和费用数据
        response.setConsumptionData(deviceDataService.getLastNDayData(StatisticTypeEnum.Water, Calendar.getInstance(), 10));
        return response;
    }

}
