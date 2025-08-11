package com.cdac.dao;

import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    Optional<ServiceProvider> findByUser(User user);
} 