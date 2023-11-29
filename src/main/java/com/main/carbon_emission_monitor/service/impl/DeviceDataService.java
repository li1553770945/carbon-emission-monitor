package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import com.main.carbon_emission_monitor.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeviceDataService implements IDeviceDataService {

    IDeviceRepo deviceRepo;

    @Autowired
    DeviceDataService(IDeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    public MeteringResponse.PeriodData getDailyData(StatisticTypeEnum type, Calendar calendar) {
        // 获取当前时间

        //获取今天的0点
        Calendar todayStart = (Calendar) calendar.clone();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todayStartDate = todayStart.getTime();

        //获取今天的24点
        Calendar todayEnd = (Calendar) todayStart.clone();
        todayEnd.add(Calendar.DAY_OF_YEAR, 1);
        Date todayEndDate = todayEnd.getTime();

        //获取昨天的0点
        Calendar yesterdayStart = (Calendar) todayStart.clone();
        yesterdayStart.add(Calendar.DAY_OF_YEAR, -1); // 昨天的0点
        Date yesterdayStartDate = yesterdayStart.getTime();

        //获取去年今天的0点
        Calendar lastYearTodayStart = (Calendar) todayStart.clone();
        lastYearTodayStart.add(Calendar.YEAR, -1);
        Date lastYearTodayStartDate = lastYearTodayStart.getTime();


        //获取去年今天的24点
        Calendar lastYearTodayEnd = (Calendar) lastYearTodayStart.clone();
        lastYearTodayEnd.add(Calendar.DAY_OF_YEAR, 1);
        Date lastYearTodayEndDate = lastYearTodayEnd.getTime();

        DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate, todayEndDate); //今日数据（截止到目前）
        DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yesterdayStartDate, todayStartDate); // 昨日数据
        DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearTodayStartDate, lastYearTodayEndDate); // 去年今日数据


        MeteringResponse.PeriodData data = new MeteringResponse.PeriodData();
        data.setAmount(today.getValue()); // 今日用量
        if(lastYearToday.getValue() == 0){
            data.setYearOverYear(0);
        } else {
            data.setYearOverYear((today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()); // 今日同比
        }

        if(yesterday.getValue() == 0){
            data.setMonthOverMonth(0);
        } else {
            data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比

        }
        return data;
    }


    public MeteringResponse.PeriodData getWeeklyData(StatisticTypeEnum type, Calendar calendar) {
        Calendar weekStart = (Calendar) calendar.clone();
        weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        weekStart.set(Calendar.HOUR_OF_DAY, 0);
        weekStart.set(Calendar.MINUTE, 0);
        weekStart.set(Calendar.SECOND, 0);
        weekStart.set(Calendar.MILLISECOND, 0);
        Date weekStartDate = weekStart.getTime();

        // 设置当前周的结束（下周一的0点）
        Calendar weekEnd = (Calendar) weekStart.clone();
        weekEnd.add(Calendar.WEEK_OF_YEAR, 1);
        Date weekEndDate = weekEnd.getTime();

        // 设置上一周的开始和结束时间
        Calendar lastWeekStart = (Calendar) weekStart.clone();
        lastWeekStart.add(Calendar.WEEK_OF_YEAR, -1);
        Date lastWeekStartDate = lastWeekStart.getTime();


        // 设置去年同一周的开始和结束时间
        Calendar lastYearWeekStart = (Calendar) weekStart.clone();
        lastYearWeekStart.add(Calendar.YEAR, -1);
        Date lastYearWeekStartDate = lastYearWeekStart.getTime();

        Calendar lastYearWeekEnd = (Calendar) lastYearWeekStart.clone();
        lastYearWeekEnd.add(Calendar.WEEK_OF_YEAR, 1);
        Date lastYearWeekEndDate = lastYearWeekEnd.getTime();

        // 获取数据
        DevicePeriodDataEntity thisWeek = deviceRepo.getPeriodDataByStatisticType(type.getValue(), weekStartDate, weekEndDate);
        DevicePeriodDataEntity lastWeek = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastWeekStartDate, weekStartDate);
        DevicePeriodDataEntity lastYearThisWeek = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearWeekStartDate, lastYearWeekEndDate);

        MeteringResponse.PeriodData data = new MeteringResponse.PeriodData();
        data.setAmount(thisWeek.getValue()); // 本周用量

        if (lastYearThisWeek.getValue() == 0) {
            data.setYearOverYear(0);
        } else {
            data.setYearOverYear((thisWeek.getValue() - lastYearThisWeek.getValue()) / lastYearThisWeek.getValue()); // 本周同比

        }

        if (lastWeek.getValue() == 0) {
            data.setMonthOverMonth(0);
        } else {
            data.setMonthOverMonth((thisWeek.getValue() - lastWeek.getValue()) / lastWeek.getValue()); // 本周环比

        }
        return data;
    }

    public MeteringResponse.PeriodData getMonthlyData(StatisticTypeEnum type, Calendar calendar) {
        // 设置当前月份的开始（月初的0点）
        Calendar monthStart = (Calendar) calendar.clone();
        monthStart.set(Calendar.DAY_OF_MONTH, 1);
        monthStart.set(Calendar.HOUR_OF_DAY, 0);
        monthStart.set(Calendar.MINUTE, 0);
        monthStart.set(Calendar.SECOND, 0);
        monthStart.set(Calendar.MILLISECOND, 0);
        Date monthStartDate = monthStart.getTime();

        // 设置当前月份的结束（下个月初的0点）
        Calendar monthEnd = (Calendar) monthStart.clone();
        monthEnd.add(Calendar.MONTH, 1);
        Date monthEndDate = monthEnd.getTime();

        // 设置上个月的开始和结束时间
        Calendar lastMonthStart = (Calendar) monthStart.clone();
        lastMonthStart.add(Calendar.MONTH, -1);
        Date lastMonthStartDate = lastMonthStart.getTime();


        // 设置去年同一月的开始和结束时间
        Calendar lastYearMonthStart = (Calendar) monthStart.clone();
        lastYearMonthStart.add(Calendar.YEAR, -1);
        Date lastYearMonthStartDate = lastYearMonthStart.getTime();

        Calendar lastYearMonthEnd = (Calendar) lastYearMonthStart.clone();
        lastYearMonthEnd.add(Calendar.MONTH, 1);
        Date lastYearMonthEndDate = lastYearMonthEnd.getTime();

        // 获取数据
        DevicePeriodDataEntity thisMonth = deviceRepo.getPeriodDataByStatisticType(type.getValue(), monthStartDate, monthEndDate);
        DevicePeriodDataEntity lastMonth = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastMonthStartDate, monthStartDate);
        DevicePeriodDataEntity lastYearThisMonth = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearMonthStartDate, lastYearMonthEndDate);

        MeteringResponse.PeriodData data = new MeteringResponse.PeriodData();
        data.setAmount(thisMonth.getValue()); // 本月用量

        if (lastYearThisMonth.getValue() == 0) {
            data.setYearOverYear(0);
        } else {
            data.setYearOverYear((thisMonth.getValue() - lastYearThisMonth.getValue()) / lastYearThisMonth.getValue()); // 本月同比

        }

        if (lastMonth.getValue() == 0) {
            data.setMonthOverMonth(0);
        } else {
            data.setMonthOverMonth((thisMonth.getValue() - lastMonth.getValue()) / lastMonth.getValue()); // 本月环比

        }
        return data;
    }

    public MeteringResponse.PeriodData getYearlyData(StatisticTypeEnum type, Calendar calendar) {
        // 设置当前年份的开始（年初的0点）
        Calendar yearStart = (Calendar) calendar.clone();
        yearStart.set(Calendar.DAY_OF_YEAR, 1);
        yearStart.set(Calendar.HOUR_OF_DAY, 0);
        yearStart.set(Calendar.MINUTE, 0);
        yearStart.set(Calendar.SECOND, 0);
        yearStart.set(Calendar.MILLISECOND, 0);
        Date yearStartDate = yearStart.getTime();

        // 设置当前年份的结束（下一年初的0点）
        Calendar yearEnd = (Calendar) yearStart.clone();
        yearEnd.add(Calendar.YEAR, 1);
        Date yearEndDate = yearEnd.getTime();

        // 设置去年同一年的开始和结束时间
        Calendar lastYearStart = (Calendar) yearStart.clone();
        lastYearStart.add(Calendar.YEAR, -1);
        Date lastYearStartDate = lastYearStart.getTime();

        Calendar lastYearEnd = (Calendar) lastYearStart.clone();
        lastYearEnd.add(Calendar.YEAR, 1);
        Date lastYearEndDate = lastYearEnd.getTime();

        // 获取数据
        DevicePeriodDataEntity thisYear = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yearStartDate, yearEndDate);
        DevicePeriodDataEntity lastYear = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearStartDate, lastYearEndDate);

        MeteringResponse.PeriodData data = new MeteringResponse.PeriodData();
        data.setAmount(thisYear.getValue()); // 本年用量
        if (lastYear.getValue() == 0) {
            data.setYearOverYear(0);
        } else {
            data.setYearOverYear((thisYear.getValue() - lastYear.getValue()) / lastYear.getValue()); // 本年同比
        }

        data.setMonthOverMonth(data.getYearOverYear());
        return data;
    }

    public List<MeteringResponse.ConsumptionData> getLastNDayData(StatisticTypeEnum type, Calendar calendar, int n) {

        Calendar todayStart = (Calendar) calendar.clone();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todayStartDate = todayStart.getTime();

        Calendar todayEnd = (Calendar) todayStart.clone();
        todayEnd.add(Calendar.DAY_OF_YEAR, 1);
        Date todayEndDate = todayEnd.getTime();

        List<MeteringResponse.ConsumptionData> dataList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            MeteringResponse.ConsumptionData data = new MeteringResponse.ConsumptionData();
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
            // 将Date对象转换为字符串
            String dateString = formatter.format(todayStartDate);
            data.setDate(dateString);
            DevicePeriodDataEntity entity = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate, todayEndDate);
            data.setConsumption(entity.getValue()); // 用电量随机生成
            data.setCost(entity.getValue() * 10);
            dataList.add(data);

            todayStart.add(Calendar.DATE, -1);
            todayStartDate = todayStart.getTime();
            todayEnd.add(Calendar.DATE, -1);
            todayEndDate = todayEnd.getTime();
        }
        return dataList;


    }
}
