package com.fdmgroup.WanderersBlog.service;

import java.util.List;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;

public interface IMessageService {

	Message saveMessage(Message message);

	List<Message> getAllMessages();

	Message getMessageById(Long id);

	void deleteMessage(Long id);

	Conversation sendMessage(Message message, String subject, Integer senderId, Integer recipientId);

}
