package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.request.RoleRequest;
import com.ricael.mergemind.dto.response.RoleResponse;
import com.ricael.mergemind.services.RoleServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Role management operations")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    private RoleServices roleServices;

    @PatchMapping("{id}")
    @Operation(summary = "Update role", description = "Updates an existing role")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role updated"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<RoleResponse> getRole(@PathVariable Long id, @RequestBody RoleRequest roleResponse) {
        return ResponseEntity.ok(roleServices.updateRole( roleResponse, id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete role", description = "Removes a role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role deleted"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleServices.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
