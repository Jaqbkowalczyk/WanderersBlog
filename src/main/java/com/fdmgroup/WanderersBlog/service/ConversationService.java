package com.fdmgroup.WanderersBlog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.User;

@Service
public class ConversationService implements IConversationService {
	@Autowired
	private ConversationRepository conversationRepository;

	@Override
	public Conversation startConversation(Conversation conversation) {
		return conversationRepository.save(conversation);
	}

	@Override
	public List<Conversation> getAllConversations() {
		return conversationRepository.findAll();
	}

	@Override
	public Conversation getConversationById(Long id) {
		return conversationRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteConversation(Long id) {
		conversationRepository.deleteById(id);
	}
	
	@Override
	public Conversation findBySubjectAndSenderAndRecipient(String subject, User sender, User recipient){
		return conversationRepository.findBySubjectAndSenderAndRecipient(subject, sender, recipient).orElse(null);
	}
	
	@Override
	public List<Conversation> findByUser(User user) {
//		List<Conversation> conversationByUser = new ArrayList<>();
//		List<Conversation> allConversations = getAllConversations();
//		
//		for (Conversation conversation : allConversations) {
//			if (conversation.getSender() == user || conversation.getRecipient() == user)
//				conversationByUser.add(conversation);
//		}
		List<Conversation> allUserConversations = conversationRepository.findBySender(user);
		allUserConversations.addAll(conversationRepository.findByRecipient(user));
		return allUserConversations;

	}

	public void updateConversation(Conversation conversation) {
		conversationRepository.save(conversation);
		
	}

}
