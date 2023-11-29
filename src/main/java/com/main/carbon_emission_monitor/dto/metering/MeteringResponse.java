package com.main.carbon_emission_monitor.dto.metering;

import lombok.Data;

import java.util.List;

@Data

public class MeteringResponse {

        private PeriodData dailyData; // 每天的数据
        private PeriodData weeklyData; // 每周的数据
        private PeriodData monthlyData; // 每月的数据
        private PeriodData yearlyData; // 每年的数据
        private List<ConsumptionData> ConsumptionData; // 最近7天每天的用电量和日期

        @Data
        public static  class ConsumptionData {
            private String date;
            private double consumption;
            private double cost;
        }
        @Data
        public  static class PeriodData {
            private double amount; // 用量
            private double yearOverYear; // 同比
            private double monthOverMonth; // 环比
        }
}
