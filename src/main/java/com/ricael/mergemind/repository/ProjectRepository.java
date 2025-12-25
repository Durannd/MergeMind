package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Status;
import org.hibernate.internal.util.collections.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByUserId(Long userId);
    List<Project> findByParticipantsId(Long userId);
    List<Project> findByStatus(Status status);
    List<Project> findByTitleContainingIgnoreCase(String title);


}
