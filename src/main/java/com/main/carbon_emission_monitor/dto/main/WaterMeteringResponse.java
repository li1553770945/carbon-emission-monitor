package com.main.carbon_emission_monitor.dto.main;

import lombok.Data;

import java.util.List;

@Data
//用水计量
public class WaterMeteringResponse {

        private List<WaterConsumptionData>  WaterConsumption;
        private List<CostData> Cost;

        @Data
        public static class   WaterConsumptionData {
            private int hour;
            private double amount;
        }

        @Data
        public static class CostData {
            private int hour;
            private double amount;
        }

}
