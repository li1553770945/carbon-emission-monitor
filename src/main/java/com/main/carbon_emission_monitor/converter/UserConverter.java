package com.main.carbon_emission_monitor.converter;

import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.po.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
   UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserEntity ToEntity(UserDO userDO);
    UserDO ToDO(UserEntity userEntity);

}
