package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.PasswordUpdateDTO;
import com.ricael.mergemind.dto.mapper.UserMapper;
import com.ricael.mergemind.dto.request.UserLoginRequest;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.request.UserUpdateRequest;
import com.ricael.mergemind.dto.response.UserResponse;
import com.ricael.mergemind.exceptions.EmailInUseException;
import com.ricael.mergemind.exceptions.NullPasswordException;
import com.ricael.mergemind.exceptions.PasswordIncorrectException;
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
        } else if (user.password() == null || user.password().isEmpty()) {
            throw new NullPasswordException("Password cannot be null or empty");
        } else {
            return UserMapper.toResponse(userRepository.save(UserMapper.toEntity(user)));
        }
    }


    //validate user to login after with jwt token
    public UserResponse validateUser(UserLoginRequest user) {
        User u = userRepository.findByEmailAndPassword(user.email(), user.password())
                .orElseThrow(() -> new ResourceNotFoundException("Email or password incorrect")
                );
        return UserMapper.toResponse(u);

    }

    public UserResponse getUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)
                );
        return UserMapper.toResponse(u);
    }

    public UserResponse updateUser(Long id, UserUpdateRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)
                );

        if (!existingUser.getEmail().equals(userRequest.email()) &&
                userRepository.existsByEmail(userRequest.email())) {
            throw new EmailInUseException("Email already in use");
        }

        if (userRequest.name() != null) existingUser.setName(userRequest.name());
        if (userRequest.email() != null) existingUser.setEmail(userRequest.email());
        if (userRequest.bio() != null) existingUser.setBio(userRequest.bio());
        if (userRequest.photo_url() != null) existingUser.setPhoto_url(userRequest.photo_url());
        if (userRequest.github_url() != null) existingUser.setGithub_url(userRequest.github_url());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(updatedUser);
    }

    public UserResponse updatePassword(Long id, PasswordUpdateDTO passwordUpdateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)
                );

        if (!existingUser.getPassword().equals(passwordUpdateDTO.currentPassword())) {
            throw new PasswordIncorrectException("Current password is incorrect");
        }

        existingUser.setPassword(passwordUpdateDTO.newPassword());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(updatedUser);
    }

}
