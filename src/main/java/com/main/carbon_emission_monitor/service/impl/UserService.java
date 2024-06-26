package com.main.carbon_emission_monitor.service.impl;
import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.enums.ErrorCodeEnums;
import com.main.carbon_emission_monitor.repo.impl.UserRepoImpl;
import com.main.carbon_emission_monitor.service.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    private final UserRepoImpl userRepo;

    UserService(UserRepoImpl userRepo){
        this.userRepo = userRepo;
    }
    public boolean Login(String username,String password){
        return false;
    }
    public long Register(UserEntity userEntity) throws BusinessException{
        if(userRepo.checkUserExistsByUsername(userEntity.getUsername())){
            throw new BusinessException(ErrorCodeEnums.USERNAME_EXIST_ERROR.getCode(),ErrorCodeEnums.USERNAME_EXIST_ERROR.getDesc());
        }
        userEntity.encryptPassword();
        userRepo.saveUser(userEntity);
        return 0;
    }
    public UserEntity GetUserInfo(String username){
        return userRepo.findUserByUsername(username);
    }
}
