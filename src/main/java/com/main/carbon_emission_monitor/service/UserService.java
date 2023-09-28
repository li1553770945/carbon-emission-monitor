package com.main.carbon_emission_monitor.service;

import com.main.carbon_emission_monitor.domain.UserEntity;

public interface UserService {
   boolean Login(String username,String password);
   long Register(UserEntity userEntity);

   UserEntity GetUserInfo(String username);
}
