package com.example.treeblog.service.user_profile;

import com.example.treeblog.dto.blog.CreateBlogDto;
import com.example.treeblog.entity.Blog;

import java.util.List;

public interface BlogInterface {

    List<Blog> getBlogsByUserId();

    Long countNumberOfPosts();

    Blog createBlog(CreateBlogDto blog);

    List<Blog> listAllBlog(Blog.Visibility visibility);
}
