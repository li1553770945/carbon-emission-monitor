package com.main.carbon_emission_monitor.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class JwtUserDetails extends User {

    public final Long id;

    public JwtUserDetails(final Long id, final String username, final String hash,
                          final Collection<? extends GrantedAuthority> authorities) {
        super(username, hash, authorities);
        this.id = id;
    }

}