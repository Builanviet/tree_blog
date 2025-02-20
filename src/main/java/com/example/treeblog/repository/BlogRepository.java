package com.example.treeblog.repository;

import com.example.treeblog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query("SELECT COUNT(b) FROM Blog b WHERE b.user.id = :userId AND b.deletedAt IS NULL")
    long countBlogsByUserIdAndDeletedAtNotNull(Integer userId);

    @Query("SELECT b FROM Blog b WHERE b.user.id = :userId AND b.deletedAt IS NULL")
    List<Blog> findBlogsByUserIdAndDeletedAtNotNull(Integer userId);

    @Query("SELECT b FROM Blog b WHERE b.deletedAt IS NULL AND " +
            "(:visibility IS NULL OR " +
            "(b.visibility = 'PUBLIC' OR (b.visibility = :visibility AND :visibility <> 'FRIEND')) OR " +
            "(b.visibility IN ('FRIEND', 'PUBLIC') AND :visibility = 'FRIEND')) " +
            "AND b.visibility <> 'PRIVATE' " +
            "ORDER BY b.createdAt DESC")
    List<Blog> findAllBlogsByVisibilityOrderByCreatedAtDesc(Blog.Visibility visibility);
}
