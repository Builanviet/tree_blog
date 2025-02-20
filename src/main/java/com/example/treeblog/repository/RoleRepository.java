package com.example.treeblog.repository;

import com.example.treeblog.entity.Role;
import com.example.treeblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}