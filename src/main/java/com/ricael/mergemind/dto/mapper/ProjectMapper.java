package com.ricael.mergemind.dto.mapper;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;

public final class ProjectMapper {
    private ProjectMapper() {
    }

    public static Project toEntity(ProjectRequest request) {
        if (request == null) {
            return null;
        }
        Project project = new Project();
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setBanner_url(request.banner_url());
        project.setStatus(request.status());

        return project;
    }

    public static ProjectResponse toResponse(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectResponse(
                project.getTitle(),
                project.getDescription(),
                project.getBanner_url(),
                project.getStatus(),
                UserMapper.toResponse(project.getUser())
        );
    }
}

