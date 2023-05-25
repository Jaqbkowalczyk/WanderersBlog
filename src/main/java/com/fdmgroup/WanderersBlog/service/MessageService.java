package com.fdmgroup.WanderersBlog.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.MessageRepository;

@Service
public class MessageService implements IMessageService {
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private OfferService offerService;
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService logService;

	@Override
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}

	@Override
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@Override
	public Message getMessageById(Long id) {
		return messageRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteMessage(Long id) {
		messageRepository.deleteById(id);
	}

	@Override
	public Conversation sendMessage(Message message, String subject, Integer senderId, Integer recipientId) {
		User sender = userService.findByUserId(senderId);
		User recipient = userService.findByUserId(recipientId);
		Conversation conversation = conversationService.findBySubjectAndSenderAndRecipient(subject, sender, recipient);

//		// Check if conversation exists between this users
//		for (Conversation senderConversation : sender.getConversations()) {
//			if (senderConversation.getSubject() == subject && ((senderConversation.getSender() == sender
//					&& senderConversation.getRecipient() == recipient)
//					|| (senderConversation.getSender() == recipient && senderConversation.getRecipient() == sender))) {
//				conversation = senderConversation;
//			}
//		}
		// Check if conversation exists between this users
		if (conversation == null) {
			conversation = conversationService.findBySubjectAndSenderAndRecipient(subject, recipient, sender);
			//If not - create a new one
			if (conversation == null) {
				conversation = new Conversation();
				conversationService.startConversation(conversation);
				conversation.setSubject(subject);
				conversation.setSender(sender);
				conversation.setRecipient(recipient);
				System.out.println("Created new conversation: " + conversation);
			}
		}
		// add the message to the conversation

		message.setConversation(conversation);
		message.setSender(sender);
		message.setRecipient(recipient);
		message.setTimeSent(new Date());
		// send the message
		// save the updated conversation
		saveMessage(message);
		conversation.addMessage(message);
		conversationService.updateConversation(conversation);

		return conversation;
	}

}