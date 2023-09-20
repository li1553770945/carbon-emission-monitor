package com.main.carbon_emission_monitor.converter;

import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.po.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
   UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    // 在这里只需要指出字段不一致的情况，支持复杂嵌套

    UserEntity ToEntity(UserDO userDO);
    UserDO ToDO(UserEntity userEntity);
}
