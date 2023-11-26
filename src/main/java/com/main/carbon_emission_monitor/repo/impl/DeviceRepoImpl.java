package com.main.carbon_emission_monitor.repo.impl;

import com.main.carbon_emission_monitor.converter.UserConverter;
import com.main.carbon_emission_monitor.dao.DeviceDAO;
import com.main.carbon_emission_monitor.dao.UserDAO;
import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.enums.DeviceTypeEnum;
import com.main.carbon_emission_monitor.po.DeviceTypeDO;
import com.main.carbon_emission_monitor.po.UserDO;
import com.main.carbon_emission_monitor.repo.IDeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepoImpl implements IDeviceRepo {

    private final DeviceDAO deviceDAO;

    @Autowired
    public DeviceRepoImpl(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<Integer> getDeviceIdListByType(DeviceTypeEnum type) {
        List<Integer> devices = this.deviceDAO.getDeviceIdListByType(type.getValue());
        List<Integer> deviceIds = new ArrayList<>();
        for (Integer deviceID : devices) {
            deviceIds.add(deviceID);
        }
        return deviceIds;

    }

}