package com.main.carbon_emission_monitor.dao;

import com.main.carbon_emission_monitor.po.DeviceDataDO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeviceDAO {
    List<Integer> getDeviceIdListByType(Integer statisticType);
    DeviceDataDO getLatestData(Integer deviceID, Date date);
}
