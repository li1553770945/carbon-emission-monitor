package com.main.carbon_emission_monitor.dto.main;

import lombok.Data;

import java.util.List;

//用水计量
@Data
public class CarbonMeteringResponse {

        private List<CarbonEmissionData>  CarbonEmission;

        @Data
        public static class   CarbonEmissionData {
            private int hour;
            private double amount;
        }


}
