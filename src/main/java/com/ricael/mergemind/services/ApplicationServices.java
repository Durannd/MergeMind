package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.Application;
import com.ricael.mergemind.domain.Role;
import com.ricael.mergemind.domain.User;
import com.ricael.mergemind.domain.enums.Status;
import com.ricael.mergemind.dto.mapper.ApplicationMapper;
import com.ricael.mergemind.dto.mapper.RoleMapper;
import com.ricael.mergemind.dto.request.ApplicationRequest;
import com.ricael.mergemind.dto.response.ApplicationResponse;
import com.ricael.mergemind.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationServices {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    UserServices userServices;

    @Autowired
    RoleServices roleServices;

    @Transactional
    public ApplicationResponse create(ApplicationRequest applicationRequest) {
        Role r = roleServices.getRoleById(applicationRequest.role().id());
        User user = userServices.getUserEntityById(applicationRequest.user().id());

        Application app = new Application();
        app.setStatus(applicationRequest.status());
        app.setRole(r);
        app.setUser(user);
        return ApplicationMapper.toResponse(applicationRepository.save(app));
    }

    public Page<ApplicationResponse> findByUserId(Long userId, Pageable pageable) {
        return applicationRepository.findByUserId(userId, pageable)
                .map(ApplicationMapper::toResponse);
    }

    public Page<ApplicationResponse> findByRoleProjectId(Long projectId, Pageable pageable) {
        return applicationRepository.findByRoleProjectId(projectId, pageable)
                .map(ApplicationMapper::toResponse);
    }

    @Transactional
    public void updateApplicationStatus(Long id, String status) {
        try{
            Status statusEnum = Status.valueOf(status.toUpperCase());
            applicationRepository.updateApplicationStatus(id, statusEnum);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }

    }

    @Transactional
    public void deleteApplication(Long id) {
        if(!applicationRepository.existsById(id)) {
            throw new IllegalArgumentException("Application with id " + id + " does not exist.");
        }
        applicationRepository.deleteById(id);
    }
}
