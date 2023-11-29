package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;

import java.util.Calendar;
import java.util.List;

public interface IDeviceDataService {
    MeteringResponse.PeriodData getDailyData(StatisticTypeEnum type, Calendar calendar);
    MeteringResponse.PeriodData getWeeklyData(StatisticTypeEnum type, Calendar calendar);
    MeteringResponse.PeriodData getMonthlyData(StatisticTypeEnum type, Calendar calendar);
    MeteringResponse.PeriodData getYearlyData(StatisticTypeEnum type, Calendar calendar);
    List<MeteringResponse.ConsumptionData> getLastNDayData(StatisticTypeEnum type, Calendar calendar,int n);
}
