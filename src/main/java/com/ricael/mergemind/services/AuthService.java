package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.mapper.UserMapper;
import com.ricael.mergemind.dto.request.UserLoginRequest;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.response.LoginResponse;
import com.ricael.mergemind.dto.response.UserResponse;
import com.ricael.mergemind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServices userServices;

    public LoginResponse login(UserLoginRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();

        String token = tokenService.generateToken(user);
        UserResponse userResponse = UserMapper.toResponse(user);

        return new LoginResponse(token, userResponse);
    }


    public LoginResponse register(UserRequest request) {
        UserRequest newUser = new UserRequest( request.name(), request.email(),
                passwordEncoder.encode(request.password()),
                null, null, null);

        User userEntity = userServices.createUserEntity(newUser);

        String token = tokenService.generateToken(userEntity);

        return new LoginResponse(token, UserMapper.toResponse(userEntity) );
    }
}