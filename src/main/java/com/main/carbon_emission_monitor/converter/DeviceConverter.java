package com.main.carbon_emission_monitor.converter;

import com.main.carbon_emission_monitor.domain.device.DeviceDataEntity;
import com.main.carbon_emission_monitor.domain.device.DevicePeriodDataEntity;
import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.po.DeviceDataDO;
import com.main.carbon_emission_monitor.po.DevicePeriodDataDO;
import com.main.carbon_emission_monitor.po.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface DeviceConverter{
    DeviceConverter INSTANCE = Mappers.getMapper(DeviceConverter.class);
    DeviceDataEntity DeviceDataDOToEntity(DeviceDataDO deviceDataDO);
    DevicePeriodDataEntity DevicePeriodDataDOToEntity(DevicePeriodDataDO devicePeriodDataDO);

}

