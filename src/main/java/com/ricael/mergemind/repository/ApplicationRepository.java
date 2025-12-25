package com.ricael.mergemind.repository;

import com.ricael.mergemind.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
