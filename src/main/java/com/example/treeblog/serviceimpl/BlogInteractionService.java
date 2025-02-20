package com.example.treeblog.serviceimpl;

import com.example.treeblog.dto.CreateBlogInterationDto;
import com.example.treeblog.entity.Blog;
import com.example.treeblog.entity.BlogInteraction;
import com.example.treeblog.entity.UserEntity;
import com.example.treeblog.repository.BlogInteractionRepository;
import com.example.treeblog.repository.BlogRepository;
import com.example.treeblog.repository.UserRepository;
import com.example.treeblog.service.user_profile.BlogInteractionInterface;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogInteractionService implements BlogInteractionInterface {

    private BlogInteractionRepository blogInteractionRepository;
    private UserRepository userRepository;
    private BlogRepository blogRepository;

    @Autowired
    public BlogInteractionService(BlogInteractionRepository blogInteractionRepository,
                                  UserRepository userRepository,
                                  BlogRepository blogRepository){
        this.blogInteractionRepository = blogInteractionRepository;
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public long countComments(int id) {
        return blogInteractionRepository.countByBlog_IdAndInteractionTypeAndCommentTextIsNotNull(id, BlogInteraction.InteractionType.COMMENT);
    }

    @Override
    public long countLike(int id) {
        return blogInteractionRepository.countByBlog_IdAndInteractionTypeAndDeletedAtIsNull(id, BlogInteraction.InteractionType.LIKE);
    }

    @Override
    public long countDislike(int id) {
        return blogInteractionRepository.countByBlog_IdAndInteractionTypeAndDeletedAtIsNull(id, BlogInteraction.InteractionType.DISLIKE);
    }

    @Override
    public long countShares(int id) {
        return blogInteractionRepository.countByBlog_IdAndInteractionTypeAndDeletedAtIsNull(id, BlogInteraction.InteractionType.SHARE);
    }

    @Override
    public BlogInteraction createBlogInteraction(CreateBlogInterationDto createBlogInterationDto) {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        BlogInteraction bi = new BlogInteraction();
        bi.setCommentText(createBlogInterationDto.getCommentText());
        bi.setInteractionType(createBlogInterationDto.getInteractionType());
        Optional<Blog> blog = blogRepository.findById(createBlogInterationDto.getBlogId());
        if(blog.isPresent()) {
            bi.setBlog(blog.get());
        }
        bi.setUser(userLogin);
        return blogInteractionRepository.save(bi);
    }

    @Override
    public List<BlogInteraction> getAllComments(int id) {
        List<BlogInteraction> blogInteractions = blogInteractionRepository.findAllByBlogIdAndInteractionType(id);
        if(blogInteractions.isEmpty()){
            throw new RuntimeException("There are no comment");
        }
        return blogInteractions;
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
