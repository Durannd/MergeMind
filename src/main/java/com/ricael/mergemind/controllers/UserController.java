package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.PasswordUpdateDTO;
import com.ricael.mergemind.dto.request.UserLoginRequest;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.request.UserUpdateRequest;
import com.ricael.mergemind.dto.response.UserResponse;
import com.ricael.mergemind.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userServices.createUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userRequest) {
        return ResponseEntity.ok(userServices.validateUser(userRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userRequest) {
        return ResponseEntity.ok(userServices.updateUser(id, userRequest));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        userServices.updatePassword(id, passwordUpdateDTO);
        return ResponseEntity.ok("Sucessfully updated password");
    }
}
