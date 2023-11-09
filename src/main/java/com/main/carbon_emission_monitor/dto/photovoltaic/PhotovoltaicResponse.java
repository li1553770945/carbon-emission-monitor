package com.main.carbon_emission_monitor.dto.photovoltaic;
import lombok.Data;
import java.util.List;


//光伏参数
@Data
public class PhotovoltaicResponse {
    private List<PowerData> PowerGenerate;
    private List<PowerData> PowerConsumption;

    private double pvGenerationAmount; // 光伏发电量
    private double pvGridConnectedAmount;// 光伏并网量
    private double pvConsumptionAmount; // 光伏用电量


    @Data
    public static class PowerData {
        private int hour;
        private double amount;
    }
}



