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


    Page<Project> findByUserId(Long userId, Pageable pageable);

    Page<Project> findByParticipantsId(Long userId, Pageable pageable);

    Page<Project> findByStatus(Status status, Pageable pageable);

    Page<Project> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Project> findAll(Pageable pageable);

    @Query(value = """
    SELECT p FROM tb_project p
    WHERE (CAST(:title AS string) IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', : title, '%')))
    AND (:status IS NULL OR p.status = :status)
    """,
            countQuery = """
    SELECT COUNT(p) FROM tb_project p
    WHERE (CAST(:title AS string) IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')))
    AND (:status IS NULL OR p.status = :status)
    """)
    Page<Project> findByDynamicFilters(
            @Param("title") String title,
            @Param("status") Status status,
            Pageable pageable
    );



}
