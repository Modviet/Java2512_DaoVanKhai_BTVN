package com.folder.productservice.repository;

import com.folder.productservice.entity.PhoneSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneSpecRepository extends JpaRepository<PhoneSpecification, UUID> {

       Optional<PhoneSpecification> findByProductId(UUID productId);

       boolean existsByProductId(UUID productId);
}
