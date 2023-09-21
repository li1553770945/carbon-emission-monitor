package com.main.carbon_emission_monitor.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    @Getter
    @Setter
    private String username;
    @Setter
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}