package com.example.treeblog.controller;

import com.example.treeblog.dto.auth.ApiResponse;
import com.example.treeblog.dto.blog.CreateBlogDto;
import com.example.treeblog.entity.Blog;
import com.example.treeblog.serviceimpl.BlogService;
import org.hibernate.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService){
        this.blogService = blogService;


    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<Blog>>> getBlogsByUserId(){
        List<Blog> blogs = blogService.getBlogsByUserId();
        if (blogs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(),"No bogs yet",null));
        }
        return ResponseEntity.ok(ApiResponse.success(blogs));
    }

    @GetMapping("/user/count")
    public ResponseEntity<ApiResponse<Integer>> countNumberOfPosts(){
        List<Blog> blogs = blogService.getBlogsByUserId();
        if (blogs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(),"No blogs yet",null));
        }
        return ResponseEntity.ok(ApiResponse.success(blogs.size()));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Blog>> createBlog(@RequestBody CreateBlogDto blog){
        Blog newBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(ApiResponse.success(newBlog));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Blog>>> getAllBlogsOrderedByCreatedAt(
            @RequestParam(value = "visibility", required = false) Blog.Visibility visibility) {
        List<Blog> blogs = blogService.listAllBlog(visibility);
        return ResponseEntity.ok(ApiResponse.success(blogs));
    }
}
