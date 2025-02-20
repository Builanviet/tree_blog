package com.example.treeblog.repository;

import com.example.treeblog.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserprofileRepository extends JpaRepository<UserProfile,Integer> {

    Optional<UserProfile> findByUserId(int id);
}
