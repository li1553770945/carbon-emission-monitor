package com.main.carbon_emission_monitor.repo.impl;

import com.main.carbon_emission_monitor.converter.DeviceConverter;
import com.main.carbon_emission_monitor.dao.DeviceDAO;
import com.main.carbon_emission_monitor.domain.device.DeviceDataEntity;
import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.po.DeviceDataDO;
import com.main.carbon_emission_monitor.po.DevicePeriodDataDO;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DeviceRepoImpl implements IDeviceRepo {

    private final DeviceDAO deviceDAO;

    @Autowired
    public DeviceRepoImpl(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<Integer> getDeviceIdListByType(Integer statisticType){
        List<Integer> devices = this.deviceDAO.getDeviceIdListByType(statisticType);
        return new ArrayList<>(devices);

    }
    public DeviceDataEntity getLatestData(Integer deviceID, Date date) {
        DeviceDataDO deviceData = deviceDAO.getLatestData(deviceID,date);
        return DeviceConverter.INSTANCE.DeviceDataDOToEntity(deviceData);
    }

    public DevicePeriodDataEntity getPeriodData(Integer deviceID, Date startDate, Date endDate) {
       DeviceDataDO start = deviceDAO.getLatestData(deviceID,startDate);
       DeviceDataDO end = deviceDAO.getLatestData(deviceID,endDate);
       DevicePeriodDataDO devicePeriodDataDO = new DevicePeriodDataDO();
       devicePeriodDataDO.setStartDatetime(startDate);
       devicePeriodDataDO.setEndDatetime(endDate);
       devicePeriodDataDO.setValue(end.getValue() - start.getValue());
       return DeviceConverter.INSTANCE.DevicePeriodDataDOToEntity(devicePeriodDataDO);
    }

    public DevicePeriodDataEntity getPeriodDataByStatisticType(Integer statisticType, Date startDate, Date endDate) {
        Double startSum = this.deviceDAO.getLatestSum(statisticType,startDate);
        Double endSum = this.deviceDAO.getLatestSum(statisticType,endDate);
        if(startSum == null){
            startSum = (double) 0;
        }
        if(endSum == null){
            endSum = (double) 0;
        }
        DevicePeriodDataDO devicePeriodDataDO = new DevicePeriodDataDO();
        devicePeriodDataDO.setStartDatetime(startDate);
        devicePeriodDataDO.setEndDatetime(endDate);
        devicePeriodDataDO.setValue(endSum - startSum);
        return DeviceConverter.INSTANCE.DevicePeriodDataDOToEntity(devicePeriodDataDO);
    }

}