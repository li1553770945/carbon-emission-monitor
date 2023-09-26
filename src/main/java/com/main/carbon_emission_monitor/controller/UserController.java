package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.dto.UserDTO;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/me")
    public ResponseResult<UserDTO> me(Authentication authentication) {

        String name = authentication.getName();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(name);
        return ResponseResult.success(userDTO);
    }

}