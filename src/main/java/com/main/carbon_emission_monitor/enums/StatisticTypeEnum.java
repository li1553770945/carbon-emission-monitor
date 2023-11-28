package com.main.carbon_emission_monitor.enums;

import lombok.Getter;

@Getter
public enum StatisticTypeEnum {
    // 用能分析之电
    Electricity("用电量",1),
    Water("用水量",2);

    private String name;
    private int value;
    StatisticTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
