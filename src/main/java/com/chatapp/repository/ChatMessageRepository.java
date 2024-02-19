package com.chatapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.model.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
	void deleteBySender(String username);

	Optional<ChatMessage> findByIdAndSender(int messageId, String username);
}