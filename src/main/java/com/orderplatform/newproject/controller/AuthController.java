package com.orderplatform.newproject.controller;

import com.orderplatform.newproject.dto.AuthResponse;
import com.orderplatform.newproject.dto.LoginRequest;
import com.orderplatform.newproject.dto.RegisterRequest;
import com.orderplatform.newproject.service.AuthService;
import com.orderplatform.newproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return this.authService.register(
                request.email(),
                request.password()
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return this.authService.login(
                request.email(),
                request.password()
        );
    }
}
