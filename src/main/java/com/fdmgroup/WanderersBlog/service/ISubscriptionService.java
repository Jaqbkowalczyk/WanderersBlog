package com.fdmgroup.WanderersBlog.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.Subscription;
import com.fdmgroup.WanderersBlog.model.User;

public interface ISubscriptionService {

	List<Subscription> findByUser(User user);

	List<Subscription> findByCreator(User creator);
	
	

	void subscribe(Subscription subscribtion);

	void unsubscribe(Subscription subscribtion);


	void notifySubscribers(User creator);
	void sendNotification(User user, User creator);

	Subscription findById(Long subscriptionId);

	Subscription findByUserAndCreator(User user, User creator);

	void checkSubscription(ModelMap model, User loggedUser, User user);

}
