package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;

import java.util.Calendar;
import java.util.List;

public interface IDeviceDataService {
    MeteringResponse.PeroidData getDailyData(StatisticTypeEnum type, Calendar calendar);
    MeteringResponse.PeroidData getWeeklyData(StatisticTypeEnum type,Calendar calendar);
    MeteringResponse.PeroidData getMonthlyData(StatisticTypeEnum type,Calendar calendar);
    MeteringResponse.PeroidData getYearlyData(StatisticTypeEnum type,Calendar calendar);
    List<MeteringResponse.ConsumptionData> getLastNDayData(StatisticTypeEnum type, Calendar calendar,int n);
}
