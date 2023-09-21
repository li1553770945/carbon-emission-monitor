package com.main.carbon_emission_monitor.domain;

import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

public class UserEntity {
    @Getter
    @Setter
    private String username;

    private String password;




    public void setPassword(String password) {

        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean CheckPassword(String password){
        return BCrypt.checkpw(password,this.password);
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
