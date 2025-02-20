package com.example.treeblog.repository;

import com.example.treeblog.entity.BlogInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogInteractionRepository extends JpaRepository<BlogInteraction, Integer> {

    long countByBlog_IdAndInteractionTypeAndCommentTextIsNotNull(Integer blogId, BlogInteraction.InteractionType interactionType);

    long countByBlog_IdAndInteractionTypeAndDeletedAtIsNull(Integer blogId, BlogInteraction.InteractionType interactionType);

    @Query("SELECT b FROM BlogInteraction b WHERE b.blog.id = :blogId AND b.interactionType = 'COMMENT' AND b.deletedAt IS NULL")
    List<BlogInteraction> findAllByBlogIdAndInteractionType(Integer blogId);
}
