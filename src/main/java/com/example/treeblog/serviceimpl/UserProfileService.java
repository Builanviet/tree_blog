package com.example.treeblog.serviceimpl;

import com.example.treeblog.config.JWTGenerator;
import com.example.treeblog.entity.UserEntity;
import com.example.treeblog.entity.UserProfile;
import com.example.treeblog.repository.UserRepository;
import com.example.treeblog.repository.UserprofileRepository;
import com.example.treeblog.service.user_profile.UserProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserProfileService implements UserProfileInterface {

    private UserprofileRepository userprofileRepository;
    private JWTGenerator jwtGenerator;
    private UserRepository userRepository;

    @Autowired
    public UserProfileService(UserprofileRepository userprofileRepository,
                              JWTGenerator jwtGenerator,
                              UserRepository userRepository){
        this.userprofileRepository = userprofileRepository;
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }


    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return (UserProfile) userprofileRepository.save(userProfile);
    }

    @Override
    public UserProfile updateUserProfile(int id, UserProfile userProfile) {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));

        UserProfile existingProfile = userprofileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find user profile id: " + id));

        if (!userLogin.getId().equals(existingProfile.getUser().getId())) {
            throw new AuthorizationDeniedException("You do not have permission for this action");
        }

        existingProfile.setAvatar(userProfile.getAvatar());
        existingProfile.setBio(userProfile.getBio());
        existingProfile.setBirthDate(userProfile.getBirthDate());
        existingProfile.setUpdatedAt(LocalDateTime.now());

        return userprofileRepository.save(existingProfile);
    }

    @Override
    public UserProfile findUserProfileById(int id) {
        Optional<UserProfile> userProfile = userprofileRepository.findById(id);
        UserProfile t = null;
        if(userProfile.isPresent()){
            t = userProfile.get();
        }
        else{
            throw new RuntimeException("Did not find user profile id: "+id);
        }
        return t;
    }

    @Override
    public UserProfile getCurrentUserProfile() {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        Optional<UserProfile> userProfile = userprofileRepository.findByUserId(userLogin.getId());
        UserProfile t = null;
        if(userProfile.isPresent()){
            t = userProfile.get();
        }
        else{
            throw new RuntimeException("Did not find user profile id: "+userLogin.getId());
        }
        return t;
    }

    @Override
    public UserProfile findUserProfileByUserId(int userId) {
        Optional<UserProfile> userProfile = userprofileRepository.findByUserId(userId);
        UserProfile t = null;
        if(userProfile.isPresent()){
            t = userProfile.get();
        }
        else{
            throw new RuntimeException("Did not find user profile id: "+userId);
        }
        return t;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }
}
