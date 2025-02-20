package com.example.treeblog.controller;

import com.example.treeblog.dto.auth.ApiResponse;
import com.example.treeblog.entity.UserProfile;
import com.example.treeblog.serviceimpl.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user_profile")
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserProfileById(@PathVariable int id){
        try {
            UserProfile userProfile = userProfileService.findUserProfileById(id);
            return ResponseEntity.ok(ApiResponse.success(userProfile));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserProfileByUserId(@PathVariable int id){
        try {
            UserProfile userProfile = userProfileService.findUserProfileByUserId(id);
            return ResponseEntity.ok(ApiResponse.success(userProfile));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<UserProfile>> getCurrentUserProfile(){
        try {
            UserProfile userProfile = userProfileService.getCurrentUserProfile();
            return ResponseEntity.ok(ApiResponse.success(userProfile));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUserProfile(@PathVariable int id, @RequestBody UserProfile userProfile) {
        try {
            UserProfile updatedProfile = userProfileService.updateUserProfile(id, userProfile);
            return ResponseEntity.ok(ApiResponse.success(updatedProfile));
        } catch (AuthorizationDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponse.error(HttpStatus.FORBIDDEN.value(), ex.getMessage(), null));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null));
        }
    }
}
