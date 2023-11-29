package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.dto.metering.MeteringResponse;
import com.main.carbon_emission_monitor.enums.StatisticTypeEnum;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import com.main.carbon_emission_monitor.service.IDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceDataService implements IDeviceDataService {

    IDeviceRepo deviceRepo;
    @Autowired
    DeviceDataService(IDeviceRepo deviceRepo){
        this.deviceRepo = deviceRepo;
    }
   public MeteringResponse.PeroidData getDailyData(StatisticTypeEnum type,Calendar calendar) {
        // 获取当前时间

       //获取今天的0点
       Calendar todayStart = (Calendar) calendar.clone();
       todayStart.set(Calendar.HOUR_OF_DAY, 0);
       todayStart.set(Calendar.MINUTE, 0);
       todayStart.set(Calendar.SECOND, 0);
       todayStart.set(Calendar.MILLISECOND, 0);
       Date todayStartDate = todayStart.getTime();

       Calendar todayEnd = (Calendar) todayStart.clone();
       todayEnd.add(Calendar.DAY_OF_YEAR,1);
       Date todayEndDate = todayEnd.getTime();

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
       System.out.println(todayEndDate);
       DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate,todayEndDate); //今日数据（截止到目前）
       DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yesterdayStartDate,todayStartDate); // 昨日数据
       DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearTodayStartDate,lastYearTodayEndDate); // 去年今日数据


       MeteringResponse.PeroidData data = new MeteringResponse.PeroidData();
       data.setAmount(today.getValue()); // 今日用量
       data.setYearOverYear( (today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()  ); // 今日同比
       data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比
       return data;
   }


    public MeteringResponse.PeroidData getWeeklyData(StatisticTypeEnum type,Calendar calendar) {
          // 获取当前时间

          //获取今天的0点
          Calendar todayStart = (Calendar) calendar.clone();
          todayStart.set(Calendar.HOUR_OF_DAY, 0);
          todayStart.set(Calendar.MINUTE, 0);
          todayStart.set(Calendar.SECOND, 0);
          todayStart.set(Calendar.MILLISECOND, 0);
          Date todayStartDate = todayStart.getTime();

          Calendar todayEnd = (Calendar) todayStart.clone();
          todayEnd.add(Calendar.DAY_OF_YEAR,1);
          Date todayEndDate = todayEnd.getTime();

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
          System.out.println(todayEndDate);
          DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate,todayEndDate); //今日数据（截止到目前）
          DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yesterdayStartDate,todayStartDate); // 昨日数据
          DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearTodayStartDate,lastYearTodayEndDate); // 去年今日数据


          MeteringResponse.PeroidData data = new MeteringResponse.PeroidData();
          data.setAmount(today.getValue()); // 今日用量
          data.setYearOverYear( (today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()  ); // 今日同比
          data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比
          return data;
     }

    public MeteringResponse.PeroidData getMonthlyData(StatisticTypeEnum type,Calendar calendar) {
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
          DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate,nowDate); //今日数据（截止到目前）
          DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yesterdayStartDate,todayStartDate); // 昨日数据
          DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearTodayStartDate,lastYearTodayEndDate); // 去年今日数据


          MeteringResponse.PeroidData data = new MeteringResponse.PeroidData();
          data.setAmount(today.getValue()); // 今日用量
          data.setYearOverYear( (today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()  ); // 今日同比
          data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比
          return data;
     }

     public MeteringResponse.PeroidData getYearlyData(StatisticTypeEnum type,Calendar calendar) {
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
          DevicePeriodDataEntity today = deviceRepo.getPeriodDataByStatisticType(type.getValue(), todayStartDate,nowDate); //今日数据（截止到目前）
          DevicePeriodDataEntity yesterday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), yesterdayStartDate,todayStartDate); // 昨日数据
          DevicePeriodDataEntity lastYearToday = deviceRepo.getPeriodDataByStatisticType(type.getValue(), lastYearTodayStartDate,lastYearTodayEndDate); // 去年今日数据


          MeteringResponse.PeroidData data = new MeteringResponse.PeroidData();
          data.setAmount(today.getValue()); // 今日用量
          data.setYearOverYear( (today.getValue() - lastYearToday.getValue()) / lastYearToday.getValue()  ); // 今日同比
          data.setMonthOverMonth((today.getValue() - yesterday.getValue()) / yesterday.getValue()); // 今日环比
          return data;
     }
     public List<MeteringResponse.ConsumptionData> getLastNDayData(StatisticTypeEnum type, Calendar calendar,int n){

         Calendar todayStart = (Calendar) calendar.clone();
         todayStart.set(Calendar.HOUR_OF_DAY, 0);
         todayStart.set(Calendar.MINUTE, 0);
         todayStart.set(Calendar.SECOND, 0);
         todayStart.set(Calendar.MILLISECOND, 0);
         Date todayStartDate = todayStart.getTime();

         Calendar todayEnd = (Calendar) todayStart.clone();
         todayEnd.add(Calendar.DAY_OF_YEAR,1);
         Date todayEndDate = todayEnd.getTime();

         List<MeteringResponse.ConsumptionData> dataList = new ArrayList<>();
         for (int i = 0; i < 7; i++) {
             MeteringResponse.ConsumptionData data = new MeteringResponse.ConsumptionData();
             data.setDate(todayStartDate.toString()); // 模拟日期
             DevicePeriodDataEntity entity = deviceRepo.getPeriodDataByStatisticType(type.getValue(),todayStartDate,todayEndDate);
             data.setConsumption(entity.getValue()); // 用电量随机生成
             data.setCost(entity.getValue()*10);
             dataList.add(data);

             todayStart.add(Calendar.DATE,-1);
             todayStartDate = todayStart.getTime();
             todayEnd.add(Calendar.DATE,-1);
             todayEndDate = todayEnd.getTime();
         }
         return dataList;



     }
}
