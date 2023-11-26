package com.main.carbon_emission_monitor.enums;

import lombok.Getter;

@Getter
public enum DeviceTypeEnum {
    Water("水",30),
    // 用能分析之电
    Electricity("电",31);

    private String name;
    private int value;
    DeviceTypeEnum(String name,int value) {
        this.name = name;
        this.value = value;
    }



}
