package com.example.treeblog.controller;

import com.example.treeblog.config.JWTGenerator;
import com.example.treeblog.dto.auth.AuthResponseDto;
import com.example.treeblog.dto.auth.LoginDto;
import com.example.treeblog.dto.auth.RegisterDto;
import com.example.treeblog.entity.Role;
import com.example.treeblog.entity.UserEntity;
import com.example.treeblog.entity.UserProfile;
import com.example.treeblog.repository.RoleRepository;
import com.example.treeblog.repository.UserRepository;
import com.example.treeblog.repository.UserprofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;
    private UserprofileRepository userprofileRepository;

    @Autowired
    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JWTGenerator jwtGenerator,
                          UserprofileRepository userprofileRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userprofileRepository = userprofileRepository;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        String username = jwtGenerator.getUsernameFromJWT(token);
        return new ResponseEntity<>(new AuthResponseDto(token,username), HttpStatus.OK);
    }


    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode((registerDto.getPassword())));

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role USER not found!"));
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        UserProfile up = new UserProfile();
        up.setUser(user);
        userprofileRepository.save(up);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
