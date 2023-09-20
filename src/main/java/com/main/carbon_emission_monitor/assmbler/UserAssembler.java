package com.main.carbon_emission_monitor.assmbler;

import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAssembler {
   UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    // 在这里只需要指出字段不一致的情况，支持复杂嵌套

    UserEntity ToEntity(UserDTO userDTO);
    UserDTO ToDTO(UserEntity userEntity);
}
