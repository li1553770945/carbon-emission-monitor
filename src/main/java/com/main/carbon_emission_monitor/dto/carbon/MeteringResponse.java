package com.main.carbon_emission_monitor.dto.carbon;

import lombok.Data;

import java.util.List;

@Data
public class MeteringResponse {

    private EnergyComparisonData dailyData; // 每天的数据
    private EnergyComparisonData weeklyData; // 每周的数据
    private EnergyComparisonData monthlyData; // 每月的数据
    private EnergyComparisonData yearlyData; // 每年的数据
    private List<ConsumptionData> ConsumptionData; // 最近7天每天的用电量和日期

    @Data
    public static  class ConsumptionData {
        private String date;
        private double consumption;
    }
    @Data
    public  static class EnergyComparisonData {
        private double amount; // 用量
        private double yearOverYear; // 同比
        private double monthOverMonth; // 环比
    }

}