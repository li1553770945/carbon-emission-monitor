package com.main.carbon_emission_monitor.domain.user;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;

@Data
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public void encryptPassword(){
        this.password = "{bcrypt}"+BCrypt.hashpw(this.password,BCrypt.gensalt());
    }

}
