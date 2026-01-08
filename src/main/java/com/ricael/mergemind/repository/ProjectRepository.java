package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.Project;
import com.ricael.mergemind.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Mantendo os métodos de busca por ID com paginação (mais performático para o Front)
    Page<Project> findByUserId(Long userId, Pageable pageable);

    Page<Project> findByParticipantsId(Long userId, Pageable pageable);

    Page<Project> findByStatus(Status status, Pageable pageable);


    Page<Project> findByTitleContainingIgnoreCase(String title, Pageable pageable);


    @Query("""
        SELECT p FROM tb_project p 
        WHERE (:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))) 
          AND (:status IS NULL OR p.status = :status)
    """)
    Page<Project> findByDynamicFilters(
            @Param("title") String title,
            @Param("status") Status status,
            Pageable pageable
    );
}
