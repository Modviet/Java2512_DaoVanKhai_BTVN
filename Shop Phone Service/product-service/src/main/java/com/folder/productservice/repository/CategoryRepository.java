package com.folder.productservice.repository;

import com.folder.productservice.entity.Category;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

      Optional<Category> findBySlug(String slug);

      boolean existsBySlug(String slug);

      boolean existsByName(String name);
}
