package com.ricael.mergemind.services;

import com.ricael.mergemind.domain.Role;
import com.ricael.mergemind.dto.mapper.RoleMapper;
import com.ricael.mergemind.dto.request.RoleRequest;
import com.ricael.mergemind.dto.response.RoleResponse;
import com.ricael.mergemind.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {

    @Autowired
    RoleRepository roleRepository;

    public RoleResponse createRole(RoleRequest roleRequest) {
        return RoleMapper.toResponse(roleRepository.save(RoleMapper.toEntity(roleRequest)));
    }

    public List<RoleResponse> listAllByProjectId(Long projectId) {
        return roleRepository.findAllByProjectId(projectId)
                .stream()
                .map(RoleMapper::toResponse)
                .toList();
    }

    public RoleResponse updateRole(RoleRequest roleRequest, Long roleId) {
        Role r = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        r.setName(roleRequest.name());
        r.setDescription(roleRequest.description());
        r.setStacks(roleRequest.stacks());
        return RoleMapper.toResponse(roleRepository.save(r));
    }

    public void deleteRole(Long roleId) {
        if(!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Role not found with id: " + roleId);
        }else{
            roleRepository.deleteById(roleId);
        }
    }

    public Role getRoleById(Long roleId) {
        Role r = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        return r;
    }


}
