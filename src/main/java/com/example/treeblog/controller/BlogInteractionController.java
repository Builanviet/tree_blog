package com.example.treeblog.controller;

import com.example.treeblog.dto.CreateBlogInterationDto;
import com.example.treeblog.dto.auth.ApiResponse;
import com.example.treeblog.entity.BlogInteraction;
import com.example.treeblog.entity.UserProfile;
import com.example.treeblog.serviceimpl.BlogInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog_interaction")
public class BlogInteractionController {

    private BlogInteractionService blogInteractionService;

    @Autowired
    public BlogInteractionController(BlogInteractionService blogInteractionService){
        this.blogInteractionService = blogInteractionService;
    }

    @GetMapping("/{id}/shares")
    public ResponseEntity<ApiResponse<Long>> countSharesByBlogId(@PathVariable int id){
        try {
            long numberOfShares = blogInteractionService.countShares(id);
            return ResponseEntity.ok(ApiResponse.success(numberOfShares));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @GetMapping("/{id}/like")
    public ResponseEntity<ApiResponse<Long>> countLikesByBlogId(@PathVariable int id){
        try {
            long numberOfShares = blogInteractionService.countLike(id);
            return ResponseEntity.ok(ApiResponse.success(numberOfShares));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<ApiResponse<Long>> countCommentsByBlogId(@PathVariable int id){
        try {
            long numberOfShares = blogInteractionService.countShares(id);
            return ResponseEntity.ok(ApiResponse.success(numberOfShares));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<List<BlogInteraction>>> getAllComments(@PathVariable int id){
        try {
            List<BlogInteraction> allComments = blogInteractionService.getAllComments(id);
            return ResponseEntity.ok(ApiResponse.success(allComments));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<BlogInteraction>> createBlogInteraction(@RequestBody CreateBlogInterationDto createBlogInterationDto){
        try {
            BlogInteraction newBlogInteraction = blogInteractionService.createBlogInteraction(createBlogInterationDto);
            return ResponseEntity.ok(ApiResponse.success(newBlogInteraction));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

}
