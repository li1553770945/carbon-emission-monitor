package com.main.carbon_emission_monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.main.carbon_emission_monitor.dao")
@SpringBootApplication
public class CarbonEmissionMonitorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarbonEmissionMonitorApplication.class, args);
	}

}
