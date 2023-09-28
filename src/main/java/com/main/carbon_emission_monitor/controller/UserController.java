package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.assmbler.UserAssembler;
import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.dto.UserResponse;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.repo.impl.UserRepoImpl;
import com.main.carbon_emission_monitor.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserServiceImpl userService;

    @Autowired
    UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseResult<UserResponse> me(Authentication authentication) {

        String username = authentication.getName();
        UserEntity userEntity = userService.GetUserInfo(username);
        UserResponse userResponse = UserAssembler.INSTANCE.ToDTO(userEntity);
        return ResponseResult.success(userResponse);
    }

}