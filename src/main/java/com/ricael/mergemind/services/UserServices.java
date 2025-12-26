package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.mapper.UserMapper;
import com.ricael.mergemind.dto.request.UserLoginRequest;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.response.UserResponse;
import com.ricael.mergemind.exceptions.EmailInUseException;
import com.ricael.mergemind.exceptions.ResourceNotFoundException;
import com.ricael.mergemind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public UserResponse createUser(UserRequest user) {
        if (userRepository.existsByEmail(user.email())) {
            throw new EmailInUseException("Email already in use");
        } else {
            return UserMapper.toResponse(userRepository.save(UserMapper.toEntity(user)));
        }
    }


    //validate user to login after with jwt token
    public UserResponse validateUser(UserLoginRequest user) {
        User u = userRepository.findByEmailAndPassword(user.email(), user.password())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.email())
        );
        return UserMapper.toResponse(u);

    }

    public UserResponse getUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)
        );
        return UserMapper.toResponse(u);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)
        );

        if (!existingUser.getEmail().equals(userRequest.email()) &&
                userRepository.existsByEmail(userRequest.email())) {
            throw new EmailInUseException("Email already in use");
        }

        existingUser.setName(userRequest.name());
        existingUser.setEmail(userRequest.email());
        existingUser.setPassword(userRequest.password());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(updatedUser);
    }


}
