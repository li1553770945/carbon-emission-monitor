package com.main.carbon_emission_monitor.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDO {
        long id;
        String username;
        String password;
        String nickname;
        LocalDateTime createAt;
        LocalDateTime updateAt;
        LocalDateTime deleteAt;

}
