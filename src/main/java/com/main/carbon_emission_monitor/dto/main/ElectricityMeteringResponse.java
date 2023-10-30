package com.main.carbon_emission_monitor.dto.main;

import lombok.Data;

import java.util.List;

//用电计量
@Data
public class ElectricityMeteringResponse {

        private List<ElectricityConsumptionData> ElectricityConsumption;
        private List<CostData> Cost;

        @Data
        public static class  ElectricityConsumptionData {
            private int hour;
            private double amount;
        }

        @Data
        public static class CostData {
            private int hour;
            private double amount;
        }

}
