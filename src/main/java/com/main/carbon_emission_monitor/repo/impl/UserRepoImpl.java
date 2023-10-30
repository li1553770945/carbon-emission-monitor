package com.main.carbon_emission_monitor.repo.impl;

import com.main.carbon_emission_monitor.converter.UserConverter;
import com.main.carbon_emission_monitor.dao.UserDAO;
import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.po.UserDO;
import com.main.carbon_emission_monitor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UserRepoImpl implements UserRepo {

    private final UserDAO userDAO;

    @Autowired
    public UserRepoImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Override
    public UserEntity findUserById(Long id) {
        UserDO userDO = userDAO.selectById(id);
        return UserConverter.INSTANCE.ToEntity(userDO);
    }

    @Override
    public UserEntity findUserByUsername(String username){
        UserDO userDO = userDAO.selectByUsername(username);
        return UserConverter.INSTANCE.ToEntity(userDO);
    }

    @Override
    public Long saveUser(UserEntity userEntity){
        UserDO userDO = UserConverter.INSTANCE.ToDO(userEntity);
        if (userDO.getId() == 0){
                userDO.setCreateAt(LocalDateTime.now());
                return userDAO.insertUser(userDO);
        } else {
                userDO.setUpdateAt(LocalDateTime.now());
                userDAO.updateUser(userDO);
                return userDO.getId();
            }
    }
    @Override
    public Boolean checkUserExistsByUsername(String username){
        return userDAO.checkUserExistsByUsername(username);
    }
}
