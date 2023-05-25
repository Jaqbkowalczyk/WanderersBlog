package com.fdmgroup.WanderersBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.MessageRepository;
import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;
import com.fdmgroup.WanderersBlog.security.UserPrincipal;

@Service
public class LoginService {

	@Autowired
	private DefaultUserDetailsService userService;

		@Autowired
		private MessageRepository messageRepository;

	public boolean isLoggedIn() {
		return SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
	}

	public User getLoggedUser() {
		if (isLoggedIn()) {
			UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			User user = userService.findByUsername(userPrincipal.getUsername()).get();
			return user;
		}
		return null;
	}

	// Perform the login check and inject user info to the header if logged in.
	public boolean isLoggedIn(ModelMap model) {

		boolean isLoggedIn = isLoggedIn();
		if (isLoggedIn) {
			UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("loggedIn", isLoggedIn);
			model.addAttribute("firstname", user.getFirstName());
			model.addAttribute("user_id", user.getId());
			model.addAttribute("loggedUser", user.getUser());
			int unread = checkUnreadMessages(getLoggedUser());
			model.addAttribute("unread", unread);
		}
		return isLoggedIn;
	}

		public int checkUnreadMessages(User user) {
			int unread = 0;
			System.out.println(user);
			List<Message> allMessages = messageRepository.findBySender(user);
			allMessages.addAll(messageRepository.findByRecipient(user));
			System.out.println("Number of messages" + allMessages.size());
			for(Message message : allMessages) {
				if(message.getRecipient().equals(user)&&!message.getIsRead()) {
					unread++;
				}
			}
			
			return unread;
		}
}
