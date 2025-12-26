package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.Application;
import com.ricael.mergemind.dto.mapper.ApplicationMapper;
import com.ricael.mergemind.dto.request.ApplicationRequest;
import com.ricael.mergemind.dto.response.ApplicationResponse;
import com.ricael.mergemind.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServices {

    @Autowired
    ApplicationRepository applicationRepository;

    public ApplicationResponse create(ApplicationRequest applicationRequest) {
        return ApplicationMapper.toResponse(applicationRepository.save(ApplicationMapper.toEntity(applicationRequest)));
    }

    public List<ApplicationResponse> findByUserId(Long userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(ApplicationMapper::toResponse)
                .toList();
    }

    public List<ApplicationResponse> findByRoleProjectId(Long projectId) {
        return applicationRepository.findByRoleProjectId(projectId)
                .stream()
                .map(ApplicationMapper::toResponse)
                .toList();
    }

    public ApplicationResponse updateApplicationStatus(Long id, String status) {
        Application updatedApplication = applicationRepository.updateApplicationStatus(id, status);
        return ApplicationMapper.toResponse(updatedApplication);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
