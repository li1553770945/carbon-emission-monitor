package com.main.carbon_emission_monitor.dto.main;

import lombok.Data;

@Data
public class TodayResponse {
    private double electricityUse;
    private double electricityYOY;//同比
    private double electricityMOM;//环比

    private double waterUse;
    private double waterYOY;//同比
    private double waterMOM;//环比

    private double carbonEmission;
    private double carbonYOY;//同比
    private double carbonMOM;//环比
}
