package com.main.carbon_emission_monitor.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String username;
    private String nickname;
    private LocalDateTime createAt;


}