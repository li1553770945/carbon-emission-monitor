package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.dto.UserDTO;
import com.main.carbon_emission_monitor.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    @Autowired
    UserController(HttpServletRequest request, UserService userService){
        this.userService = userService;
    }
    @ResponseBody
    @RequestMapping(value="/login",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
    public String login(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUsername());
        return "success";
    }
}