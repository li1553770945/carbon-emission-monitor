package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.dto.electricity.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import com.main.carbon_emission_monitor.repo.impl.DeviceRepoImpl;
import com.main.carbon_emission_monitor.repo.impl.UserRepoImpl;
import com.main.carbon_emission_monitor.service.IElectricityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ElectricityService implements IElectricityService {


    private final IDeviceRepo deviceRepo;


    @Autowired
    public ElectricityService(IDeviceRepo deviceRepo){
        this.deviceRepo = deviceRepo;
    }
    public MeteringResponse Metering(){
        MeteringResponse response = generateTestData();
        return response;
    }

    public MeteringResponse generateTestData() {
        MeteringResponse response = new MeteringResponse();

        // 生成每天、每周、每月、每年的能耗数据
        response.setDailyData(generateEnergyComparisonData());
        response.setWeeklyData(generateEnergyComparisonData());
        response.setMonthlyData(generateEnergyComparisonData());
        response.setYearlyData(generateEnergyComparisonData());

        // 生成最近7天的用电量和费用数据
        response.setConsumptionData(generateConsumptionDataList());
        response.setCostData(generateCostDataList());
        return response;
    }

    private  MeteringResponse.EnergyComparisonData generateEnergyComparisonData() {

        // 获取当前时间
        Calendar nowCalendar = Calendar.getInstance();
        Date nowDate = nowCalendar.getTime();


        //获取今天的0点
        Calendar todayStart = (Calendar) nowCalendar.clone();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Date todayStartDate = todayStart.getTime();

        //获取昨天的0点
        Calendar yesterdayStart =  (Calendar)todayStart.clone();
        yesterdayStart.add(Calendar.DAY_OF_YEAR,-1); // 昨天的0点
        Date yesterdayStartDate = yesterdayStart.getTime();

        //获取去年今天的0点
        Calendar lastYearTodayStart =  (Calendar)todayStart.clone();
        lastYearTodayStart.add(Calendar.YEAR,-1);
        Date lastYearTodayStartDate = lastYearTodayStart.getTime();


        //获取去年今天的24点
        Calendar lastYearTodayEnd =  (Calendar)lastYearTodayStart.clone();
        lastYearTodayEnd.add(Calendar.DAY_OF_YEAR,1);
        Date lastYearTodayEndDate = lastYearTodayEnd.getTime();

        System.out.println(lastYearTodayStartDate);
        System.out.println(lastYearTodayEndDate);
        System.out.println(yesterdayStartDate);
        System.out.println(todayStartDate);
        System.out.println(nowDate);
        DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(StatisticTypeEnum.Electricity.getValue(), todayStartDate,nowDate); //今日数据（截止到目前）
        DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(StatisticTypeEnum.Electricity.getValue(), yesterdayStartDate,todayStartDate); // 昨日数据
        DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(StatisticTypeEnum.Electricity.getValue(), lastYearTodayStartDate,lastYearTodayEndDate); // 去年今日数据


        MeteringResponse.EnergyComparisonData data = new MeteringResponse.EnergyComparisonData();
        data.setAmount(today.getValue()); // 今日用量
        data.setYearOverYear( (today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()  ); // 今日同比
        data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比
        return data;
    }

    private static List<MeteringResponse.ConsumptionData> generateConsumptionDataList() {
        List<MeteringResponse.ConsumptionData> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            MeteringResponse.ConsumptionData data = new MeteringResponse.ConsumptionData();
            data.setDate("2023-10-" + (i + 1)); // 模拟日期
            data.setConsumption(random.nextDouble() * 100); // 用电量随机生成
            dataList.add(data);
        }
        return dataList;
    }
    private static List<MeteringResponse.CostData> generateCostDataList() {
        List<MeteringResponse.CostData> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            MeteringResponse.CostData data = new MeteringResponse.CostData();
            data.setDate("2023-10-" + (i + 1)); // 模拟日期
            data.setCost(random.nextDouble() * 50); // 费用随机生成
            dataList.add(data);
        }
        return dataList;
    }
}
