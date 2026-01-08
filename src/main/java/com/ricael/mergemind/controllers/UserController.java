package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.PasswordUpdateDTO;
import com.ricael.mergemind.dto.request.UserLoginRequest;
import com.ricael.mergemind.dto.request.UserRequest;
import com.ricael.mergemind.dto.request.UserUpdateRequest;
import com.ricael.mergemind.dto.response.UserResponse;
import com.ricael.mergemind.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Tag(name = "Users", description = "User management operations")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Creates a new user and returns its data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "409", description = "Email already in use")
    })
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userServices.createUser(userRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Validates credentials and returns user data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful login"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userRequest) {
        return ResponseEntity.ok(userServices.validateUser(userRequest));
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get user by ID", description = "Returns a user by its identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @PatchMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Update user", description = "Updates user profile data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userRequest) {
        return ResponseEntity.ok(userServices.updateUser(id, userRequest));
    }

    @PatchMapping("/{id}/password")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Change password", description = "Updates the user's password")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Password updated"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        userServices.updatePassword(id, passwordUpdateDTO);
        return ResponseEntity.ok("Sucessfully updated password");
    }
}
