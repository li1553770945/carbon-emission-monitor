package com.main.carbon_emission_monitor.repo;

import com.main.carbon_emission_monitor.domain.user.UserEntity;

public interface IUserRepo {
    UserEntity findUserById(Long id);
    UserEntity findUserByUsername(String userName);
    Long saveUser(UserEntity userEntity);
    Boolean checkUserExistsByUsername(String username);
}
