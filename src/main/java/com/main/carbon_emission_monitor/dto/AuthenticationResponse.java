package com.main.carbon_emission_monitor.dto;

import lombok.Getter;
import lombok.Setter;


public class AuthenticationResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
