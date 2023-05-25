package com.fdmgroup.WanderersBlog.service;

import java.util.List;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.User;

public interface IConversationService {

	Conversation startConversation(Conversation conversation);

	List<Conversation> getAllConversations();

	Conversation getConversationById(Long id);

	void deleteConversation(Long id);
	
	List<Conversation> findByUser(User user);

	Conversation findBySubjectAndSenderAndRecipient(String subject, User sender, User recipient);

}
