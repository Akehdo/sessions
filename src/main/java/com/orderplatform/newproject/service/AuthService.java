package com.orderplatform.newproject.service;

import com.orderplatform.newproject.domain.User;
import com.orderplatform.newproject.dto.AuthResponse;
import com.orderplatform.newproject.exception.InvalidCredentialsException;
import com.orderplatform.newproject.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersService usersService;
    private  final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(String email, String password) {
        //if user with email exist
        if(usersService.existByEmail(email)) {
            throw new UserAlreadyExistException(email);
        }

        //hash password
        String hashedPassword = passwordEncoder.encode(password);

        //create user
        User user = usersService.create(email, hashedPassword);

        //generate tokens
        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);

        //TODO create -> store ref token


        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(String email, String password) {
        //if user exists
        User user = usersService.findByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);

        //TODO create -> store ref token

        return new AuthResponse(accessToken, refreshToken);
    }


    //TODO logout logic (deleting or revoking currently used reftoken)

    //TODO refresh logic (by using refresh token generate new access token and refresh token)
}
