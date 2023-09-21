package com.main.carbon_emission_monitor.dto;


import lombok.Getter;

public class AuthenticationRequest {


    private String login;

    private String password;

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

}
