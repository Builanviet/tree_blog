package com.example.treeblog.dto;

import com.example.treeblog.entity.BlogInteraction;

public class CreateBlogInterationDto {

    private String commentText;
    private BlogInteraction.InteractionType interactionType;
    private int blogId;

    public CreateBlogInterationDto(String commentText, BlogInteraction.InteractionType interactionType, int blogId) {
        this.commentText = commentText;
        this.interactionType = interactionType;
        this.blogId = blogId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public BlogInteraction.InteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(BlogInteraction.InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
