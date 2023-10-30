package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.dto.main.*;
import com.main.carbon_emission_monitor.service.MainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {
   public PhotovoltaicParameterResponse PhotovoltaicParameter(){
        PhotovoltaicParameterResponse response = new PhotovoltaicParameterResponse();

        List<PhotovoltaicParameterResponse.PowerData> powerGenerate = new ArrayList<>();
        List<PhotovoltaicParameterResponse.PowerData> powerConsumption = new ArrayList<>();

        for (int hour = 0; hour < 7; hour++) {
            // 创建用于PowerGenerate的测试数据
            PhotovoltaicParameterResponse.PowerData generateData = new PhotovoltaicParameterResponse.PowerData();
            generateData.setHour(hour);
            generateData.setAmount(Math.random() * 100); // 随机生成数值
            powerGenerate.add(generateData);

            // 创建用于PowerConsumption的测试数据
            PhotovoltaicParameterResponse.PowerData consumptionData = new PhotovoltaicParameterResponse.PowerData();
            consumptionData.setHour(hour);
            consumptionData.setAmount(Math.random() * 50); // 随机生成数值
            powerConsumption.add(consumptionData);
        }

        response.setPowerGenerate(powerGenerate);
        response.setPowerConsumption(powerConsumption);
        return response;
    }
    public ElectricityMeteringResponse ElectricityMetering(){
        ElectricityMeteringResponse response = new ElectricityMeteringResponse();
        List<ElectricityMeteringResponse.ElectricityConsumptionData> electricityConsumptionList = new ArrayList<>();
        List<ElectricityMeteringResponse.CostData> costList = new ArrayList<>();

        for (int hour = 1; hour <= 24; hour++) {
            ElectricityMeteringResponse.ElectricityConsumptionData electricityData = new ElectricityMeteringResponse.ElectricityConsumptionData();
            electricityData.setHour(hour);
            electricityData.setAmount(Math.random() * 100); // You can modify the range as needed.
            electricityConsumptionList.add(electricityData);

            ElectricityMeteringResponse.CostData costData = new ElectricityMeteringResponse.CostData();
            costData.setHour(hour);
            costData.setAmount(Math.random() * 50); // You can modify the range as needed.
            costList.add(costData);
        }

        response.setElectricityConsumption(electricityConsumptionList);
        response.setCost(costList);

        return response;

    }

    public WaterMeteringResponse WaterMetering(){
        WaterMeteringResponse response = new WaterMeteringResponse();
        List<WaterMeteringResponse.WaterConsumptionData> waterConsumptionList = new ArrayList<>();
        List<WaterMeteringResponse.CostData> costList = new ArrayList<>();

        for (int hour = 1; hour <= 24; hour++) {
            WaterMeteringResponse.WaterConsumptionData waterData = new WaterMeteringResponse.WaterConsumptionData();
            waterData.setHour(hour);
            waterData.setAmount(Math.random() * 100); // You can modify the range as needed.
            waterConsumptionList.add(waterData);

            WaterMeteringResponse.CostData costData = new WaterMeteringResponse.CostData();
            costData.setHour(hour);
            costData.setAmount(Math.random() * 50); // You can modify the range as needed.
            costList.add(costData);
        }

        response.setWaterConsumption(waterConsumptionList);
        response.setCost(costList);
        return response;
    }
    public CarbonMeteringResponse CarbonMetering(){

        CarbonMeteringResponse response = new CarbonMeteringResponse();
        List<CarbonMeteringResponse.CarbonEmissionData> carbonEmissionList = new ArrayList<>();

        for (int hour = 1; hour <= 24; hour++) {
            CarbonMeteringResponse.CarbonEmissionData carbonData = new CarbonMeteringResponse.CarbonEmissionData();
            carbonData.setHour(hour);
            carbonData.setAmount(Math.random() * 100); // You can modify the range as needed.
            carbonEmissionList.add(carbonData);
        }

        response.setCarbonEmission(carbonEmissionList);

        return response;
    }
    public TodayResponse Today(){
        TodayResponse response = new TodayResponse();

        response.setElectricityUse(Math.random() * 100); // Modify the range as needed.
        response.setElectricityYOY(Math.random() * 10);
        response.setElectricityMOM(Math.random() * 5);

        response.setWaterUse(Math.random() * 50); // Modify the range as needed.
        response.setWaterYOY(Math.random() * 10);
        response.setWaterMOM(Math.random() * 5);

        response.setCarbonEmission(Math.random() * 200); // Modify the range as needed.
        response.setCarbonYOY(Math.random() * 20);
        response.setCarbonMOM(Math.random() * 10);

        return response;
    }
    public IndoorEnvironmentResponse IndoorEnvironment(String area){
        IndoorEnvironmentResponse response = new IndoorEnvironmentResponse();

        response.setHumidity(Math.random() * 100); // Modify the range as needed.
        response.setPM25(Math.random() * 100); // Modify the range as needed.
        response.setTemperature(Math.random() * 30); // Modify the range as needed.
        response.setCO2(Math.random() * 1000); // Modify the range as needed.

        return response;
    }
}
