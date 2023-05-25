package com.fdmgroup.WanderersBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.Subscription;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.SubscriptionRepository;

@Service
public class SubscriptionService implements ISubscriptionService {
	
	@Autowired
	private SubscriptionRepository repo;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IStoryService storyService;
	
	
	@Override
	public List<Subscription> findByUser(User user) {

		return repo.findByUser(user);
	}
	
	@Override
	public List<Subscription> findByCreator(User creator) {

		return repo.findByCreator(creator);
	}
	
	@Override
	public void subscribe(Subscription subscribtion) {
		repo.save(subscribtion);
	}
	
	@Override
	public void unsubscribe(Subscription subscribtion) {
		repo.delete(subscribtion);
	}
	
	@Override
	public Subscription findByUserAndCreator(User user, User creator) {

		return repo.findByUserAndCreator(user, creator).orElse(null);
	}
	
	@Override
	public void notifySubscribers(User creator) {
		List<Subscription> creatorSubscriptions = repo.findByCreator(creator);
		for(Subscription subscription : creatorSubscriptions) {
			if(subscription.getNotificationsOn()) {
				sendNotification(subscription.getUser(), creator);
			}
		}
		
	}
	@Override
	public void sendNotification(User user, User creator) {
		Message message = new Message();
		User wanderer = userService.findByUsername("Wanderer");
		String title = "New post from " + creator.getUsername() + "!";
		List<Story> creatorStories = storyService.findByUser(creator);
		Story latestStory = creatorStories.get(creatorStories.size()-1);
		message.setContent(""
				+ "<br>"
				+ "There is a new post by your favourite "+creator.getUsername()+"!"
				+ "<br>"
				+ "<br>"
				+ "Check it out: <a href=\"/story/"+ latestStory.getId() +"\">" + latestStory.getTitle() +"</a>"+ "<br>"
				+ "<br>"
				+ "See their profile for more: <a href=\"/userProfile/"+ creator.getUsername() +"\">"+ creator.getUsername() +"</a>" + "<br>"
				+ "<br>"
				+ "<br>"
				);
		messageService.sendMessage(message, title, wanderer.getId(), user.getId());	
	}

	@Override
	public Subscription findById(Long subscriptionId) {
		return repo.findById(subscriptionId).orElse(null);
	}

	@Override
	public void checkSubscription(ModelMap model, User user, User creator) {
		Subscription subscription = findByUserAndCreator(user, creator);
		if(subscription !=null) {
			model.addAttribute("subscription", subscription);
		}
		
	}
}
