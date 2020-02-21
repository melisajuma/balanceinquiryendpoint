package com.imbank.ussdbackend.repository;

import com.imbank.ussdbackend.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
