package com.main.carbon_emission_monitor.dao;

import com.main.carbon_emission_monitor.enums.DeviceTypeEnum;
import com.main.carbon_emission_monitor.po.StatisticDO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceDAO {
    List<Integer> getDeviceIdListByType(int type);
    List <StatisticDO> getLatestNData(int type,int n);
    List <StatisticDO> getDataByDate(int type, LocalDateTime date);
}
