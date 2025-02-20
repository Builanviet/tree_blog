package com.example.treeblog.service.user_profile;

import com.example.treeblog.dto.CreateBlogInterationDto;
import com.example.treeblog.entity.Blog;
import com.example.treeblog.entity.BlogInteraction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogInteractionInterface {

    long countComments(int id);

    long countLike(int id);

    long countDislike(int id);

    long countShares(int id);

    BlogInteraction createBlogInteraction(CreateBlogInterationDto createBlogInterationDto);

    List<BlogInteraction> getAllComments(int id);
}
