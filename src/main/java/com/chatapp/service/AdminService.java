package com.chatapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.chatapp.model.ChatMessage;
import com.chatapp.repository.ChatMessageRepository;

@Service
public class AdminService {
     
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    
    
    // Method to retrieve all users from the database
    public List<String> getAllUsers() {
        try {
            return chatMessageRepository.findAll().stream()
                    .map(ChatMessage::getSender) // Extract usernames
                    .distinct() // Filter out duplicates
                    .collect(Collectors.toList()); // Collect unique usernames into a list
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while retrieving users: " + e.getMessage(), e);
        }
    }
    
    // Method to retrieve messages by username
    public List<String> getMessagesByUsername(String username) {
        try {
            return chatMessageRepository.findAll().stream()
                    .filter(message -> message.getSender().equals(username)) // Filter messages by username
                    .map(ChatMessage::getContent) // Map messages to their content
                    .collect(Collectors.toList()); // Collect messages into a list
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while retrieving messages for user: " + username, e);
        }
    }
    
    // Method to delete all messages by username
    public void deleteMessageByUsername(String username) {
        try {
            chatMessageRepository.deleteBySender(username);
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while deleting messages for user: " + username, e);
        }
    }
    
    
    // Method to delete a message by its ID and associated username
    public void deleteMessageByIdAndUsername(int messageId, String username) {
        try {
            Optional<ChatMessage> messageToDelete = chatMessageRepository.findByIdAndSender(messageId, username);
            if (messageToDelete.isPresent()) {
                chatMessageRepository.delete(messageToDelete.get());
            } else {
                throw new IllegalArgumentException("Message not found with ID: " + messageId + " for user: " + username);
            }
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while deleting message with ID: " + messageId + " for user: " + username, e);
        }
    }
    
    // Method to update a message by its ID
    public ChatMessage updateMessageById(int id, ChatMessage updatedMessage) {
        try {
            Optional<ChatMessage> optionalMessage = chatMessageRepository.findById(id);
            if (optionalMessage.isPresent()) {
                ChatMessage existingMessage = optionalMessage.get();
                existingMessage.setContent(updatedMessage.getContent());
                return chatMessageRepository.save(existingMessage);
            } else {
                throw new IllegalArgumentException("Message not found with ID: " + id);
            }
        } catch (DataAccessException e) {
            // Handle database access exception
            throw new RuntimeException("Error occurred while updating message with ID: " + id, e);
        }
    }
}
