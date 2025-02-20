package com.example.treeblog.controller;

import com.example.treeblog.dto.auth.ApiResponse;
import com.example.treeblog.entity.GroupChat;
import com.example.treeblog.serviceimpl.GroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group_chat")
public class GroupChatController {

    private GroupChatService groupChatService;

    @Autowired
    public GroupChatController(GroupChatService groupChatService){
        this.groupChatService = groupChatService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse<GroupChat>> createGroupChat(@RequestBody GroupChat groupChat){
        try {
            GroupChat gc = groupChatService.createGroupChat(groupChat);
            return ResponseEntity.ok(ApiResponse.success(gc));
        } catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null));
        }
    }
}
