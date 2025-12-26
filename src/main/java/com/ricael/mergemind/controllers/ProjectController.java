package com.ricael.mergemind.controllers;

import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.request.UserRefRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;
import com.ricael.mergemind.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ProjectResponse>> getByDynamicFilters(@RequestParam(required = false) String title, @RequestParam(required = false) Status status) {
        return ResponseEntity.ok(projectServices.findByDynamicFilters(title, status));
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
    public ResponseEntity<List<ProjectResponse>> getProjectsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(projectServices.findByTitleContainingIgnoreCase(title));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<ProjectResponse>> getProjectsByStatus(@RequestParam Status status) {
        return ResponseEntity.ok(projectServices.findByStatus(status));
    }

    @GetMapping("by-participants")
    public ResponseEntity<List<ProjectResponse>> getProjectsByParticipantsId(@RequestParam Long userId) {
        return ResponseEntity.ok(projectServices.findByParticipantsId(new UserRefRequest(userId)));
    }


}
