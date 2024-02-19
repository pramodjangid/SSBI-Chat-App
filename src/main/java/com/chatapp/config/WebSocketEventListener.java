package com.chatapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chatapp.model.ChatMessage;
import com.chatapp.model.MessageType;

/**
 * WebSocketEventListener listens for WebSocket events like disconnects and performs necessary actions.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Handles WebSocket disconnect events and notifies other users.
     *
     * @param event SessionDisconnectEvent representing the disconnect event.
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // Wrap the event message in StompHeaderAccessor for easier access to headers and attributes
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // Retrieve the username from the session attributes
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            // Log the disconnection event
            log.info("User disconnected: {}", username);
            // Create a ChatMessage of type LEAVE to notify other users about the disconnection
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            // Send the LEAVE message to the "/topic/public" destination
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
