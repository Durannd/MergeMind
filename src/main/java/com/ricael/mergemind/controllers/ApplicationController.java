package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.request.ApplicationRequest;
import com.ricael.mergemind.dto.request.StatusUpdateRequest;
import com.ricael.mergemind.dto.response.ApplicationResponse;
import com.ricael.mergemind.services.ApplicationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationServices applicationServices;

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@RequestBody ApplicationRequest applicationRequest) {
        return ResponseEntity.ok(applicationServices.create(applicationRequest));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(applicationServices.findByUserId(id));
    }

    @GetMapping("project/{id}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationByProjectId(@PathVariable Long id) {
        return ResponseEntity.ok(applicationServices.findByRoleProjectId(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateApplicationStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest status) {
        applicationServices.updateApplicationStatus(id, status.status());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationServices.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

}
