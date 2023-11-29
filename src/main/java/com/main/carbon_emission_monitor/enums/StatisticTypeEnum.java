package com.main.carbon_emission_monitor.enums;

import lombok.Getter;

@Getter
public enum StatisticTypeEnum {
    Electricity("用电量",1),
    Water("用水量",2),
    Carbon("碳排放量",3);

    private final String name;
    private final int value;
    StatisticTypeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
