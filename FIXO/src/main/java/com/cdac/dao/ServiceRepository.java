package com.cdac.dao;

import com.cdac.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Add custom query methods if needed
} 