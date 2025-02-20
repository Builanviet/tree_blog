package com.example.treeblog.dto.blog;

import com.example.treeblog.entity.Blog;

public class CreateBlogDto {

    private String title;
    private String content;
    private Blog.Visibility visibility;
    private Boolean isShared;
    private int userId;

    public CreateBlogDto(String title, String content, Blog.Visibility visibily, Boolean isShared, int userId) {
        this.title = title;
        this.content = content;
        this.visibility = visibily;
        this.isShared = isShared;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog.Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Blog.Visibility visibily) {
        this.visibility = visibily;
    }

    public Boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
