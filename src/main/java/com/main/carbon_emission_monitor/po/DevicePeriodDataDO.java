package com.main.carbon_emission_monitor.po;

import lombok.Data;

import java.util.Date;

@Data
public class DevicePeriodDataDO {
        Date startDatetime;
        Date endDatetime;
        double value;
}
