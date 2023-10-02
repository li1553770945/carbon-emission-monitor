package com.main.carbon_emission_monitor.dao;

import com.main.carbon_emission_monitor.po.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    UserDO selectById(long id);
    UserDO selectByUsername(String username);
    Long insertUser(UserDO userDO);

    Boolean checkUserExistsByUsername(String username);
    void updateUser(UserDO userDO);
}
