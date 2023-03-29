package com.example.demo.Controller;

import com.example.demo.Request.ResetPasswordRequest;
import com.example.demo.Request.UserLoginRequest;
import com.example.demo.Request.UserRegisterRequest;
import com.example.demo.Response.UserAuthResponse;

import com.example.demo.Service.AuthService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserAuthResponse login(@RequestBody UserLoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterRequest registerRequest){ authService.register(registerRequest); }

    @PostMapping("/reset-password")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(userService.getAuthenticatedUserId(), resetPasswordRequest);
    }

}

