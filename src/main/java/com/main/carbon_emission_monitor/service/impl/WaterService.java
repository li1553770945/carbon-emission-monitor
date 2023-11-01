package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.dto.water.MeteringResponse;
import com.main.carbon_emission_monitor.service.IWaterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WaterService implements IWaterService {
    public MeteringResponse Metering(){
        MeteringResponse response = generateTestData();
        return response;
    }

    public static MeteringResponse generateTestData() {
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

    private static MeteringResponse.EnergyComparisonData generateEnergyComparisonData() {
        MeteringResponse.EnergyComparisonData data = new MeteringResponse.EnergyComparisonData();
        Random random = new Random();
        data.setAmount(random.nextDouble() * 1000); // 用量随机生成
        data.setYearOverYear(random.nextDouble() * 100); // 同比随机生成
        data.setMonthOverMonth(random.nextDouble() * 10); // 环比随机生成
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
