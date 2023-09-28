package com.main.carbon_emission_monitor.repo.impl;

import com.main.carbon_emission_monitor.converter.UserConverter;
import com.main.carbon_emission_monitor.dao.UserDAO;
import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.po.UserDO;
import com.main.carbon_emission_monitor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

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
        System.out.println(userDO);
        System.out.println(UserConverter.INSTANCE.ToEntity(userDO));
        return UserConverter.INSTANCE.ToEntity(userDO);
    }

    @Override
    public long saveUser(UserEntity userEntity){
        System.out.println(userEntity);
        UserDO userDO = UserConverter.INSTANCE.ToDO(userEntity);
        if (userDO.getId() == 0){
                return userDAO.insertUser(userDO);
        } else {
                //TODO: 修改用户没有实现
                return 0;
            }
    }
}
