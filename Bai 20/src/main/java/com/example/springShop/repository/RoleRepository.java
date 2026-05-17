package com.example.springShop.repository;

import com.example.springShop.entity.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    Role save(Role role);
}
