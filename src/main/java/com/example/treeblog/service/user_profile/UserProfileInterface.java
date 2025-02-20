package com.example.treeblog.service.user_profile;

import com.example.treeblog.entity.UserProfile;

public interface UserProfileInterface {

    UserProfile createUserProfile(UserProfile userProfile);

    UserProfile updateUserProfile(int id, UserProfile userProfile);

    UserProfile findUserProfileById(int id);

    UserProfile getCurrentUserProfile();

    UserProfile findUserProfileByUserId(int userId);
}
