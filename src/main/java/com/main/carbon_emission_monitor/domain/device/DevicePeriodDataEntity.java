package com.main.carbon_emission_monitor.domain.device;

import lombok.Data;

import java.util.Date;


@Data
public class DevicePeriodDataEntity  {

    Date startDatetime;
    Date endDatetime;
    double value;
}
