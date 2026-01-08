package com.ricael.mergemind.controllers;

import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.RoleGetResponse;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.request.UserRefRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;
import com.ricael.mergemind.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectServices projectServices;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectServices.createProject(projectRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectServices.findById(id));
    }

    @GetMapping("/filters")
    public ResponseEntity<Page<ProjectResponse>> getByDynamicFilters(@RequestParam(required = false) String title,
                                                                     @RequestParam(required = false) Status status,
                                                                     @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByDynamicFilters(title, status, pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody ProjectRequest projectRequest) {

        return ResponseEntity.ok(projectServices.updateProject(id, projectRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectServices.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

    @GetMapping("/by-title")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByTitle(@RequestParam String title,
                                                                    @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByTitleContainingIgnoreCase(title, pageable));
    }

    @GetMapping("/by-status")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByStatus(@RequestParam Status status,
                                                                     @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByStatus(status, pageable));
    }

    @GetMapping("by-participants")
    public ResponseEntity<Page<ProjectResponse>> getProjectsByParticipantsId(@RequestParam Long userId,
                                                                             @PageableDefault(size = 20, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(projectServices.findByParticipantsId(new UserRefRequest(userId), pageable));
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RoleGetResponse>> getRolesByProjectId(@PathVariable Long id) {
        return ResponseEntity.ok(projectServices.findRolesByProjectId(id));
    }

    @PostMapping("{id}/roles")
    public ResponseEntity<RoleGetResponse> addRoleToProject(@PathVariable Long id, @RequestBody RoleGetResponse roleResponse) {
        return ResponseEntity.ok(projectServices.addRoleToProject(id, roleResponse));
    }

}
