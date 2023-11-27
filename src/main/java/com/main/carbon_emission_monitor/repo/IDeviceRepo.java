package com.main.carbon_emission_monitor.repo;

import com.main.carbon_emission_monitor.domain.device.DeviceDataEntity;
import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;

import java.util.Date;
import java.util.List;

public interface IDeviceRepo {
    List<Integer> getDeviceIdListByType(Integer statisticType);
    DeviceDataEntity getLatestData(Integer DeviceID, Date date);

    DevicePeriodDataEntity getPeriodData(Integer deviceID, Date startDate, Date endDate);

}
