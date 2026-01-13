package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query(value = """
    SELECT up.project_id, u.*
    FROM tb_user u 
    JOIN user_project up ON u.id = up.user_id 
    WHERE up.project_id = :id
    """, nativeQuery = true)
    Page<User> findParticipantsByProjectId(Long id, Pageable pageable);
}
