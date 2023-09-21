package com.main.carbon_emission_monitor.service.impl;

import com.main.carbon_emission_monitor.domain.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Collections;
@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;

    // ...

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {
        final List<SimpleGrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority("user"));
        System.out.println(username);
        return new JwtUserDetails(1L, username, "{noop}123456",roles );
    }

}