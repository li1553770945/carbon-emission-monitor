package com.main.carbon_emission_monitor.controller;
import com.main.carbon_emission_monitor.dto.LoginRequest;
import com.main.carbon_emission_monitor.dto.LoginResponse;
import com.main.carbon_emission_monitor.service.impl.JwtTokenService;
import com.main.carbon_emission_monitor.service.impl.JwtUserDetailsService;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    final JwtTokenService jwtTokenService;
    final JwtUserDetailsService jwtUserDetailsService;
    final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtTokenService jwtTokenService, JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseResult<LoginResponse> authenticate(@RequestBody  final LoginRequest authenticationRequest) {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final LoginResponse authenticationResponse = new LoginResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));

        return ResponseResult.success(authenticationResponse);
    }

}
