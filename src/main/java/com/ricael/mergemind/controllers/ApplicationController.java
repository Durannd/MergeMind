package com.ricael.mergemind.controllers;

import com.ricael.mergemind.dto.request.ApplicationRequest;
import com.ricael.mergemind.dto.request.StatusUpdateRequest;
import com.ricael.mergemind.dto.response.ApplicationResponse;
import com.ricael.mergemind.services.ApplicationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@Tag(name = "Applications", description = "Applications to project roles")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {

    @Autowired
    private ApplicationServices applicationServices;

    @PostMapping
    @Operation(summary = "Create application", description = "Creates an application for a role in a project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Application created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<ApplicationResponse> createApplication(@RequestBody ApplicationRequest applicationRequest) {
        return ResponseEntity.ok(applicationServices.create(applicationRequest));
    }

    @GetMapping("user/{id}")
    @Operation(summary = "List applications by user", description = "Returns applications of a user with pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Applications page")
    })
    public ResponseEntity<Page<ApplicationResponse>> getApplicationByUserId(@PathVariable Long id,
                                                                            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(applicationServices.findByUserId(id, pageable));
    }

    @GetMapping("project/{id}")
    @Operation(summary = "List applications by project", description = "Returns applications of a project with pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Applications page")
    })
    public ResponseEntity<Page<ApplicationResponse>> getApplicationByProjectId(@PathVariable Long id,
                                                                               @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(applicationServices.findByRoleProjectId(id, pageable));
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update application status", description = "Updates the status of an application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Application not found")
    })
    public ResponseEntity<Void> updateApplicationStatus(@PathVariable Long id, @RequestBody StatusUpdateRequest status) {
        applicationServices.updateApplicationStatus(id, status.status());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete application", description = "Removes an application by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Application deleted"),
            @ApiResponse(responseCode = "404", description = "Application not found")
    })
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationServices.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

}
