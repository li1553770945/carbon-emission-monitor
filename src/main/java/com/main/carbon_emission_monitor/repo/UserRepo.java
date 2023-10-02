package com.main.carbon_emission_monitor.repo;

import com.main.carbon_emission_monitor.domain.UserEntity;

public interface UserRepo {
    UserEntity findUserById(Long id);
    UserEntity findUserByUsername(String userName);
    Long saveUser(UserEntity userEntity);
    Boolean checkUserExistsByUsername(String username);
}
