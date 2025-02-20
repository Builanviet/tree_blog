package com.example.treeblog.dto.user_profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RequestUpdateDto {

    private String avatar;
    private String bio;
    private LocalDateTime birthdate;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }
}
