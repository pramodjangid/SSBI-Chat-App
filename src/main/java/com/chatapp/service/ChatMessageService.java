package com.chatapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.chatapp.model.ChatMessage;
import com.chatapp.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
     
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	// Method to save a chat message
    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        try {
            return chatMessageRepository.save(chatMessage);
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while saving chat message: " + e.getMessage(), e);
        }
    }
    
}
