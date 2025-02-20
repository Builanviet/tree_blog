package com.example.treeblog.serviceimpl;

import com.example.treeblog.dto.blog.CreateBlogDto;
import com.example.treeblog.entity.Blog;
import com.example.treeblog.entity.UserEntity;
import com.example.treeblog.repository.BlogRepository;
import com.example.treeblog.repository.UserRepository;
import com.example.treeblog.service.user_profile.BlogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements BlogInterface {

    private final BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository,UserRepository userRepository){
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Blog> getBlogsByUserId() {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        return blogRepository.findBlogsByUserIdAndDeletedAtNotNull(userLogin.getId());
    }

    @Override
    public Long countNumberOfPosts() {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        return blogRepository.countBlogsByUserIdAndDeletedAtNotNull(userLogin.getId());
    }

    @Override
    public Blog createBlog(CreateBlogDto blog) {
        String username = getCurrentUsername();
        UserEntity userLogin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found for username: " + username));
        Blog newBlog = new Blog();
        newBlog.setTitle(blog.getTitle());
        newBlog.setContent(blog.getContent());
        newBlog.setVisibility(Blog.Visibility.valueOf(blog.getVisibility().toString().toUpperCase()));
        newBlog.setShared(false);
        Optional<UserEntity> ue = userRepository.findById(userLogin.getId());
        if(ue.isPresent()){
            newBlog.setUser(ue.get());
        }

        return (Blog) blogRepository.save(newBlog);
    }

    @Override
    public List<Blog> listAllBlog(Blog.Visibility visibility) {
        return blogRepository.findAllBlogsByVisibilityOrderByCreatedAtDesc(visibility);
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
