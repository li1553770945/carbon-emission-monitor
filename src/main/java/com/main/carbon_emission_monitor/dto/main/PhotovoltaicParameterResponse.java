package com.main.carbon_emission_monitor.dto.main;
import lombok.Data;
import java.util.List;


//光伏参数
@Data
public class PhotovoltaicParameterResponse {
    private List<PowerData> PowerGenerate;
    private List<PowerData> PowerConsumption;

    @Data
    public static class PowerData {
        private int hour;
        private double amount;
    }
}



