package com.main.carbon_emission_monitor.controller;

import com.main.carbon_emission_monitor.assmbler.UserAssembler;
import com.main.carbon_emission_monitor.domain.user.UserEntity;
import com.main.carbon_emission_monitor.dto.basic.BusinessException;
import com.main.carbon_emission_monitor.dto.basic.ErrorCodeEnums;
import com.main.carbon_emission_monitor.dto.user.LoginRequest;
import com.main.carbon_emission_monitor.dto.user.LoginResponse;
import com.main.carbon_emission_monitor.dto.user.RegisterRequest;
import com.main.carbon_emission_monitor.dto.user.UserResponse;
import com.main.carbon_emission_monitor.dto.basic.ResponseResult;
import com.main.carbon_emission_monitor.service.UserService;
import com.main.carbon_emission_monitor.service.impl.JwtTokenService;
import com.main.carbon_emission_monitor.service.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    final JwtTokenService jwtTokenService;
    final JwtUserDetailsService jwtUserDetailsService;
    final AuthenticationManager authenticationManager;

    @Autowired
    UserController(UserService userService, JwtTokenService jwtTokenService, JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager){
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseResult<Void> register(@Validated @RequestBody final RegisterRequest registerRequest, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()){
            throw  new BusinessException(ErrorCodeEnums.PARAM_VALIDATE_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        UserEntity userEntity = UserAssembler.INSTANCE.RegisterReqToEntity(registerRequest);
        long id = userService.Register(userEntity);
        return ResponseResult.success();
    }
    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody  final LoginRequest authenticationRequest, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()){
            throw  new BusinessException(ErrorCodeEnums.PARAM_VALIDATE_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex) {
            throw new BusinessException(ErrorCodeEnums.FORBIDDEN_ERROR.getCode(),"用户名或密码错误");
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final LoginResponse authenticationResponse = new LoginResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        return ResponseResult.success(authenticationResponse);
    }

    @GetMapping("/me")
    public ResponseResult<UserResponse> me(Authentication authentication) {

        String username = authentication.getName();
        UserEntity userEntity = userService.GetUserInfo(username);
        UserResponse userResponse = UserAssembler.INSTANCE.ToDTO(userEntity);
        return ResponseResult.success(userResponse);
    }

}