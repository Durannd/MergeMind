package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.RoleGetResponse;
import com.ricael.mergemind.dto.request.RoleRequest;
import com.ricael.mergemind.dto.response.RoleResponse;
import com.ricael.mergemind.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleServices roleServices;

    @PatchMapping("{id}")
    public ResponseEntity<RoleResponse> getRole(@PathVariable Long id, @RequestBody RoleRequest roleResponse) {
        return ResponseEntity.ok(roleServices.updateRole( roleResponse, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleServices.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
