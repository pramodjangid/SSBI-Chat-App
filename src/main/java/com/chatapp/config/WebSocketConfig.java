package com.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Register STOMP endpoints (WebSocket endpoints) allowing WebSocket clients to connect to the server
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS(); // Register the WebSocket endpoint and enable SockJS fallback options
    }

    // Configure message broker options
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Set the prefix for messages that the server will handle
        registry.setApplicationDestinationPrefixes("/app");

        // Enable a simple in-memory message broker to send messages to clients on destinations prefixed with "/topic"
        registry.enableSimpleBroker("/topic");
    }
}
