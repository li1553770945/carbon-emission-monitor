package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.assmbler.UserAssembler;
import com.main.carbon_emission_monitor.converter.UserConverter;
import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.dto.LoginResponse;
import com.main.carbon_emission_monitor.dto.RegisterRequest;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.po.UserDO;
import com.main.carbon_emission_monitor.repo.UserRepo;
import com.main.carbon_emission_monitor.service.UserService;
import com.main.carbon_emission_monitor.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    UserServiceImpl userService;

    @Autowired
    RegisterController(UserServiceImpl userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody final RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        UserEntity userEntity = UserAssembler.INSTANCE.RegisterReqToEntity(registerRequest);
        System.out.println(userEntity);
        long id = userService.Register(userEntity);
        return ResponseResult.success();
    }
}
