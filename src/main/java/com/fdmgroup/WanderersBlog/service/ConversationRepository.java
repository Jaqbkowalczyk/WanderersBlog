package com.fdmgroup.WanderersBlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.User;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

	Optional<Conversation> findBySubjectAndSenderAndRecipient(String subject, User sender, User recipient);

	List<Conversation> findBySender(User user);
	List<Conversation> findByRecipient(User user);

}
