package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.JwtUserDetails;
import com.main.carbon_emission_monitor.domain.UserEntity;
import com.main.carbon_emission_monitor.repo.impl.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Collections;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepoImpl userRepoImpl;

    @Autowired
    public JwtUserDetailsService(UserRepoImpl userRepoImpl) {
        this.userRepoImpl = userRepoImpl;
    }


    // ...

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {

        final List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("user"));
        UserEntity userEntity = this.userRepoImpl.findUserByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new JwtUserDetails(userEntity.getId(), username, userEntity.getPassword(), roles);

    }

}