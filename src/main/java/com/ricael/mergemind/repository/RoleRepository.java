package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findAllByProjectId(Long projectId, Pageable pageable);

}
