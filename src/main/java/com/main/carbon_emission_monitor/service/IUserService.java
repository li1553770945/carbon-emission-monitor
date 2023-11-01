package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;

public interface IUserService {
   boolean Login(String username,String password);
   long Register(UserEntity userEntity) throws BusinessException;

   UserEntity GetUserInfo(String username);
}
