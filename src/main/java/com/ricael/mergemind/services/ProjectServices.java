package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.mapper.ProjectMapper;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.request.UserRefRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;
import com.ricael.mergemind.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {

    @Autowired
    ProjectRepository projectRepository;

    public ProjectResponse createProject(ProjectRequest projectRequest) {

        return ProjectMapper.toResponse(projectRepository.save(ProjectMapper.toEntity(projectRequest)));
    }

    public List<ProjectResponse> findByStatus(Status status) {
        return projectRepository.findByStatus(status)
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    public List<ProjectResponse> findByParticipantsId(UserRefRequest userRefRequest) {
        return projectRepository.findByParticipantsId(userRefRequest.id())
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    public List<ProjectResponse> findByTitleContainingIgnoreCase(String title) {
        return projectRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    public List<ProjectResponse> findByUserId(UserRefRequest userRefRequest) {
        return projectRepository.findByUserId(userRefRequest.id())
                .stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

    public ProjectResponse updateProject(ProjectRequest projectRequest) {
        return ProjectMapper.toResponse(projectRepository.save(ProjectMapper.toEntity(projectRequest)));
    }

    public ProjectResponse findById(Long id) {
        return ProjectMapper.toResponse(projectRepository.findById(id).orElseThrow());
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }



}
