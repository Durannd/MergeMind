package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.Role;
import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.RoleGetResponse;
import com.ricael.mergemind.dto.mapper.ProjectMapper;
import com.ricael.mergemind.dto.mapper.RoleMapper;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.request.UserRefRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;
import com.ricael.mergemind.dto.response.RoleResponse;
import com.ricael.mergemind.exceptions.TitleBlankOrNullException;
import com.ricael.mergemind.repository.ProjectRepository;
import com.ricael.mergemind.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public ProjectResponse createProject(ProjectRequest projectRequest) {

        return ProjectMapper.toResponse(projectRepository.save(ProjectMapper.toEntity(projectRequest)));
    }

    public Page<ProjectResponse> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable)
                .map(ProjectMapper::toResponse);
    }

    public Page<ProjectResponse> findByStatus(Status status, Pageable pageable) {
        return projectRepository.findByStatus(status, pageable)
                .map(ProjectMapper::toResponse);
    }

    public Page<ProjectResponse> findByParticipantsId(UserRefRequest userRefRequest, Pageable pageable) {
        return projectRepository.findByParticipantsId(userRefRequest.id(), pageable)
                .map(ProjectMapper::toResponse);
    }

    public Page<ProjectResponse> findByTitleContainingIgnoreCase(String title, Pageable pageable) {
        return projectRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(ProjectMapper::toResponse);
    }


    public Page<ProjectResponse> findByUserId(UserRefRequest userRefRequest, Pageable pageable) {
        return projectRepository.findByUserId(userRefRequest.id(), pageable )
                .map(ProjectMapper::toResponse);
    }

    @Transactional
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {

        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        if (projectRequest.title().isBlank() || projectRequest.title() == null) {
            throw new TitleBlankOrNullException("Title cannot be blank");
        }
        existingProject.setTitle(projectRequest.title());
        existingProject.setDescription(projectRequest.description());
        existingProject.setBanner_url(projectRequest.banner_url());
        existingProject.setStatus(projectRequest.status());
        return ProjectMapper.toResponse(projectRepository.save(existingProject));
    }

    public ProjectResponse findById(Long id) {
        return ProjectMapper.toResponse(projectRepository.findById(id).orElseThrow());
    }

    public Page<ProjectResponse> findByDynamicFilters(String title, Status status, Pageable pageable) {
        return projectRepository.findByDynamicFilters(title, status, pageable)
                .map(ProjectMapper::toResponse);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<RoleGetResponse> findRolesByProjectId(Long projectId) {
        return roleRepository.findAllByProjectId(projectId)
                .stream()
                .map(RoleMapper::toGetResponse)
                .toList();
    }

    public RoleGetResponse addRoleToProject(Long projectId, RoleGetResponse roleGetResponse) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Role r = new Role();
        r.setName(roleGetResponse.name());
        r.setDescription(roleGetResponse.description());
        r.setStacks(roleGetResponse.stacks());
        r.setProject(project);
        Role savedRole = roleRepository.save(r);
        return RoleMapper.toGetResponse(savedRole);
    }

}
