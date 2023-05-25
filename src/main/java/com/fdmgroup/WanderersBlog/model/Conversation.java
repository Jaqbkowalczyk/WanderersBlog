package com.fdmgroup.WanderersBlog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String subject;

	@OneToMany(mappedBy = "conversation")
	private List<Message> messages;

	@OneToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	@OneToOne
	@JoinColumn(name = "recipient_id")
	private User recipient;

	public Conversation() {
	}

	public Conversation(String subject, User sender, User recipient) {
		super();
		this.subject = subject;
		setSender(sender);
		setRecipient(recipient);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
		sender.addConversation(this);
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
		recipient.addConversation(this);
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(messages, recipient, sender, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversation other = (Conversation) obj;
		return Objects.equals(messages, other.messages) && Objects.equals(recipient, other.recipient)
				&& Objects.equals(sender, other.sender) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "Conversation [id=" + id + ", subject=" + subject + ", sender=" + sender + ", recipient=" + recipient
				+ "]";
	}

	

}
