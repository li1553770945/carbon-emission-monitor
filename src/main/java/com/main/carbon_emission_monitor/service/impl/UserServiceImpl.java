package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.repo.UserRepo;
import com.main.carbon_emission_monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    public boolean Login(String username, String password){
        UserEntity userEntity = userRepo.findUserByUsername(username);
        return userEntity.CheckPassword(password);
    }
}
