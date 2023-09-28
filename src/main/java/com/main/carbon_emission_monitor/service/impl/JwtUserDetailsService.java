package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.JwtUserDetails;
import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.repo.impl.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Collections;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;
    private final UserRepoImpl userRepoImpl;

    @Autowired
    public JwtUserDetailsService(UserRepoImpl userRepoImpl) {
        this.userRepoImpl = userRepoImpl;
    }


    // ...

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {
        try {
            final List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("user"));
            UserEntity userEntity = this.userRepoImpl.findUserByUsername(username);
            return new JwtUserDetails(userEntity.getId(), username, "{noop}"+userEntity.getPassword(), roles);
        } catch (Exception e) {
            // 记录异常信息
            e.printStackTrace();
            throw e;
        }
    }

}