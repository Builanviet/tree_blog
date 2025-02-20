package com.example.treeblog.serviceimpl;

import com.example.treeblog.entity.GroupChat;
import com.example.treeblog.repository.GroupChatRepository;
import com.example.treeblog.service.user_profile.GroupChatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupChatService implements GroupChatInterface {

    private GroupChatRepository groupChatRepository;

    @Autowired
    public GroupChatService(GroupChatRepository groupChatRepository){
        this.groupChatRepository = groupChatRepository;
    }

    @Override
    public GroupChat createGroupChat(GroupChat groupChat) {
        return groupChatRepository.save(groupChat);
    }
}
