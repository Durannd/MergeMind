package com.ricael.mergemind.dto.mapper;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.dto.request.ProjectRequest;
import com.ricael.mergemind.dto.response.ProjectResponse;

public final class ProjectMapper {
    private ProjectMapper() {
    }

    public static Project toEntity(ProjectRequest response) {
        if (response == null) {
            return null;
        }
        Project project = new Project();
        project.setTitle(response.title());
        project.setDescription(response.description());
        project.setBanner_url(response.banner_url());
        project.setStatus(response.status());
        project.setUser(new User());
        project.getUser().setId(response.user().id());

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

