package com.chatapp.controller;

import com.chatapp.model.ChatMessage;
import com.chatapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    
    private AdminService adminService;
    
    @Autowired
    public AdminController(AdminService adminService) {
		this.adminService=adminService;
	}


	// Endpoint to retrieve all users
    @GetMapping("/users")
    public ResponseEntity<List<String>> getAllUsers() {
        List<String> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    

    // Endpoint to retrieve messages by username
    @GetMapping("/messages/{username}")
    public ResponseEntity<List<String>> getMessagesByUsername(@PathVariable String username) {
        List<String> messages = adminService.getMessagesByUsername(username);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    

    
    // Endpoint to delete all messages by username
    @DeleteMapping("/messages/{username}")
    public ResponseEntity<Void> deleteMessageByUsername(@PathVariable String username) {
        adminService.deleteMessageByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    
 // Endpoint to delete a message by username and id
    @DeleteMapping("/messages/{id}/{username}")
    public ResponseEntity<String> deleteMessageByIdAndUsername(@PathVariable int id, @PathVariable String username) {
        adminService.deleteMessageByIdAndUsername(id, username);
        return new ResponseEntity<>("Message with ID " + id + " for user " + username + " has been deleted.", HttpStatus.OK);
    }

    
    
    // Endpoint to update a message by its ID
    @PutMapping("/messages/{id}")
    public ResponseEntity<ChatMessage> updateMessageById(@PathVariable int id, @RequestBody ChatMessage updatedMessage) {
        ChatMessage updated = adminService.updateMessageById(id, updatedMessage);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
