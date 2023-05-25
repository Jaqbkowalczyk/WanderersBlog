package com.fdmgroup.WanderersBlog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.ConversationService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.MessageService;
import com.fdmgroup.WanderersBlog.service.OfferService;
import com.fdmgroup.WanderersBlog.service.UserService;

@Controller
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService logService;
	@Autowired
	private OfferService offerService;

	@GetMapping("/inbox")
	public String getMessages(ModelMap model) {
		logService.isLoggedIn(model);
		User user = logService.getLoggedUser();
		List<Conversation> conversations = conversationService.findByUser(user);
		List<Integer> unreadMessages = new ArrayList<>();
		List<String> lastMessages = new ArrayList<>();
		List<Conversation> convToDelete = new ArrayList<>();
		for (Conversation conversation : conversations) {
			//If conversation is empty it should be deleted.
			if (conversation.getMessages().size() > 0) {
				lastMessages.add(conversation.getMessages().get(conversation.getMessages().size() - 1).getContent());
				unreadMessages.add((Integer) checkUnreadMessagesInConversation(conversation, user));
			}else {
				convToDelete.add(conversation);
			}
		}
		//Delete empty conversations
		for (Conversation conversation : convToDelete) {
			conversations.remove(conversation);
		}
		
		//Reverse conversation list to show most recent conversations first
	    Collections.reverse(conversations);
	    Collections.reverse(unreadMessages);
	    Collections.reverse(lastMessages);
		model.addAttribute("unreadMarks", unreadMessages);
		model.addAttribute("conversations", conversations);
		model.addAttribute("lastMessages", lastMessages);

		return "inbox";
	}

	@GetMapping("/conversation/{conversationId}")
	public String goToConversation(ModelMap model, @PathVariable Long conversationId) {
		logService.isLoggedIn(model);
		Conversation conversation = conversationService.getConversationById(conversationId);

		return populateConversation(model, conversation);
	}

	@PostMapping("/sendMessage/{offerId}/{recipientId}")
	public String sendMessageModel(@ModelAttribute Message message, @PathVariable Integer offerId,
			@PathVariable Integer recipientId, ModelMap model) {
		logService.isLoggedIn(model);
		Offer offer = offerService.findById(offerId);
		Conversation conversation = messageService.sendMessage(message, offer.getTitle(),
				logService.getLoggedUser().getId(), recipientId);
		return populateConversation(model, conversation);
	}

	@PostMapping("/sendMessage/{conversationId}")
	public String sendMessageInConversationModel(@ModelAttribute Message message, @PathVariable Long conversationId,
			ModelMap model) {
		logService.isLoggedIn(model);
		Conversation conversation = conversationService.getConversationById(conversationId);
		User sender = logService.getLoggedUser();
		User recipient;
		if (sender == conversation.getSender()) {
			recipient = conversation.getRecipient();
		} else {
			recipient = conversation.getSender();
		}
		message.setConversation(conversation);
		message.setSender(sender);
		message.setRecipient(recipient);
		message.setTimeSent(new Date());
		messageService.saveMessage(message);
		conversation.addMessage(message);
		conversationService.updateConversation(conversation);

		return populateConversation(model, conversation);
	}

	public String populateConversation(ModelMap model, Conversation conversation) {
		for (Message message : conversation.getMessages()) {
			message = messageService.getMessageById(message.getId());
			if (logService.getLoggedUser().equals(conversation.getSender())) {
				if (message.getSender().equals(conversation.getRecipient())) {
					message.setIsRead(true);
					messageService.saveMessage(message);
				}
			} else if (logService.getLoggedUser().equals(conversation.getRecipient())) {
				if (message.getSender().equals(conversation.getSender())) {
					message.setIsRead(true);
					messageService.saveMessage(message);
				}
			} else {
				// if you are not in conversation you cannot access it.
				return "index";
			}
		}
		model.addAttribute("conversationId", conversation.getId());
		model.addAttribute("subject", conversation.getSubject());
		model.addAttribute("sender", conversation.getSender());
		model.addAttribute("recipient", conversation.getRecipient());
		model.addAttribute("messages", conversation.getMessages());
		return "conversation";
	}

	public int checkUnreadMessagesInConversation(Conversation conversation, User user) {
		int unread = 0;
		System.out.println(user);
		List<Message> allMessages = conversation.getMessages();

		for (Message message : allMessages) {
			if (message.getRecipient().equals(user) && !message.getIsRead()) {
				unread++;
			}
		}

		return unread;
	}

}
