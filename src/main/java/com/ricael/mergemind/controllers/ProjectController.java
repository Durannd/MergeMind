package com.ricael.mergemind.controllers;

import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.RoleGetResponse;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.request.UserRefRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;
import com.ricael.mergemind.dto.response.ProjectWithIdResponse;
import com.ricael.mergemind.dto.response.UserParticipantResponse;
import com.ricael.mergemind.services.ProjectServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Project management operations")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;

    @PostMapping
    @Operation(summary = "Create project", description = "Creates a project associated with an owner user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectServices.createProject(projectRequest));
    }

    @GetMapping
    @Operation(summary = "List projects", description = "Returns a page of projects with sorting and pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(@PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findAll(pageable));
    }

    @GetMapping("/id")
    @Operation(summary = "List projects with id", description = "Returns a page of projects with sorting and pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Page<ProjectWithIdResponse>> getAllProjectsWithId(@PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findAllWithId(pageable));
    }

    @GetMapping("/user")
    @Operation(summary = "List projects by user", description = "Filters projects by owner user ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Page<ProjectResponse>> getProjectsByUserId(@RequestBody UserRefRequest userId,
                                                                    @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByUserId(userId, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Returns a project by its identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project found"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectServices.findById(id));
    }

    @GetMapping("/filters")
    @Operation(summary = "Filter projects", description = "Filters projects by title and status with pagination")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Page<ProjectResponse>> getByDynamicFilters(@RequestParam(required = false) String title,
                                                                     @RequestParam(required = false) Status status,
                                                                     @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByDynamicFilters(title, status, pageable));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update project", description = "Updates fields of an existing project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest projectRequest) {

        return ResponseEntity.ok(projectServices.updateProject(id, projectRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project", description = "Removes a project by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Project deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectServices.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

    @GetMapping("/by-title")
    @Operation(summary = "Search by title", description = "Searches projects whose title contains the given text")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page")
    })
    public ResponseEntity<Page<ProjectResponse>> getProjectsByTitle(@RequestParam String title,
                                                                    @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByTitleContainingIgnoreCase(title, pageable));
    }

    @GetMapping("/by-status")
    @Operation(summary = "Search by status", description = "Searches projects by status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page")
    })
    public ResponseEntity<Page<ProjectResponse>> getProjectsByStatus(@RequestParam Status status,
                                                                     @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByStatus(status, pageable));
    }

    @GetMapping("by-participants")
    @Operation(summary = "Search by participant", description = "Lists projects in which the user participates")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projects page")
    })
    public ResponseEntity<Page<ProjectResponse>> getProjectsByParticipantsId(@RequestParam Long userId,
                                                                             @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByParticipantsId(new UserRefRequest(userId), pageable));
    }

    @GetMapping("/{id}/roles")
    @Operation(summary = "List project roles", description = "Returns roles associated with a project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Roles page")
    })
    public ResponseEntity<Page<RoleGetResponse>> getRolesByProjectId(@PathVariable Long id, @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findRolesByProjectId(id, pageable));
    }

    @PostMapping("{id}/roles")
    @Operation(summary = "Add role to project", description = "Adds an existing role to a project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role added"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<RoleGetResponse> addRoleToProject(@PathVariable Long id, @RequestBody RoleGetResponse roleResponse) {
        return ResponseEntity.ok(projectServices.addRoleToProject(id, roleResponse));
    }

    @GetMapping("participants/{projectId}")
    @Operation(summary = "List project participants", description = "Returns participants associated with a project")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Participants page")
    })
    public ResponseEntity<Page<UserParticipantResponse>> getParticipantsByProjectId(@PathVariable Long projectId, @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findParticipantsByProjectId(projectId, pageable));
    }

}
