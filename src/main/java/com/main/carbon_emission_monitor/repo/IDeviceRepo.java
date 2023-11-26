package com.main.carbon_emission_monitor.repo;

import com.main.carbon_emission_monitor.enums.DeviceTypeEnum;

import java.util.List;

public interface IDeviceRepo {
    List<Integer> getDeviceIdListByType(DeviceTypeEnum type);

}
