package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.Application;
import com.ricael.mergemind.domain.enums.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByUserId(Long userId);
    List<Application> findByRoleProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query("UPDATE tb_application a SET a.status = :status WHERE a.id = :id")
    void updateApplicationStatus(Long id, Status status);

}
