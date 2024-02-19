package com.chatapp;

import com.chatapp.controller.AdminController;
import com.chatapp.model.ChatMessage;
import com.chatapp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminControllerTest {

    private AdminService adminService;
    private AdminController adminController;

    @BeforeEach
    public void init() {
        // Initialize the AdminService and AdminController
        adminService = new AdminService();
        adminController = new AdminController(adminService);
    }

    @Test
    public void testGetAllUsers() {
        List<String> users = new ArrayList<>();
        users.add("user1");
        users.add("user2");

        ResponseEntity<List<String>> responseEntity = adminController.getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    public void testGetMessagesByUsername() {
        String username = "user1";
        List<String> messages = new ArrayList<>();
        messages.add("Message 1");
        messages.add("Message 2");

        ResponseEntity<List<String>> responseEntity = adminController.getMessagesByUsername(username);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(messages, responseEntity.getBody());
    }

    @Test
    public void testDeleteMessageByUsername() {
        String username = "user1";

        ResponseEntity<Void> responseEntity = adminController.deleteMessageByUsername(username);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteMessageByIdAndUsername() {
        int id = 1;
        String username = "user1";

        ResponseEntity<String> responseEntity = adminController.deleteMessageByIdAndUsername(id, username);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUpdateMessageById() {
        int id = 1;
        ChatMessage message = new ChatMessage();
        message.setId(id);
        message.setContent("Updated content");

        ResponseEntity<ChatMessage> responseEntity = adminController.updateMessageById(id, message);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(message, responseEntity.getBody());
    }
}
