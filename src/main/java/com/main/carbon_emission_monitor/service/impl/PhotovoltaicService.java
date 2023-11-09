package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.dto.photovoltaic.PhotovoltaicResponse;
import com.main.carbon_emission_monitor.service.IPhotovoltaicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PhotovoltaicService implements IPhotovoltaicService {
    public PhotovoltaicResponse Metering(){
        return generatePVResponse();
    }
    public static PhotovoltaicResponse generatePVResponse() {
        PhotovoltaicResponse response = new PhotovoltaicResponse();
        response.setPowerGenerate(generatePowerDataList());
        response.setPowerConsumption(generatePowerDataList());

        // Assuming you want to sum the generated power for this example
        response.setPvGenerationAmount(response.getPowerGenerate().stream().mapToDouble(PhotovoltaicResponse.PowerData::getAmount).sum());
        response.setPvGridConnectedAmount(response.getPvGenerationAmount() * 0.8); // example calculation
        response.setPvConsumptionAmount(response.getPowerConsumption().stream().mapToDouble(PhotovoltaicResponse.PowerData::getAmount).sum());

        return response;
    }

    private static List<PhotovoltaicResponse.PowerData> generatePowerDataList() {
        List<PhotovoltaicResponse.PowerData> powerDataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 24; i++) {
            PhotovoltaicResponse.PowerData data = new PhotovoltaicResponse.PowerData();
            data.setHour(i);
            data.setAmount(random.nextDouble() * 100); // Generates a random amount between 0 and 100
            powerDataList.add(data);
        }
        return powerDataList;
    }

}
