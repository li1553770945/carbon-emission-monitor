package com.main.carbon_emission_monitor.po;

import lombok.Data;
import java.util.Date;

@Data
public class DeviceDataDO {
    int id;
    Date datetime;
    double value;
}