package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;

public interface UserService {
   boolean Login(String username,String password);
   long Register(UserEntity userEntity) throws BusinessException;

   UserEntity GetUserInfo(String username);
}
