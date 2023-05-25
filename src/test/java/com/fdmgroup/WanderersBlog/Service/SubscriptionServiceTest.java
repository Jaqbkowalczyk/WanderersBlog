package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

import com.fdmgroup.WanderersBlog.model.Subscription;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.SubscriptionRepository;
import com.fdmgroup.WanderersBlog.service.MessageService;
import com.fdmgroup.WanderersBlog.service.StoryService;
import com.fdmgroup.WanderersBlog.service.SubscriptionService;
import com.fdmgroup.WanderersBlog.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class SubscriptionServiceTest {

	@InjectMocks
	SubscriptionService subscriptionService;
	
	@MockBean
	SubscriptionRepository mockSubscriptionRepository;
	
	@MockBean
	MessageService mockMessageService;
	
	@MockBean
	UserService mockUserService;
	
	@MockBean
	StoryService mockStoryService;
	
	List<Subscription> expectedSubscriptions; 
	
	@BeforeEach
	public void setUp() {
		expectedSubscriptions = new ArrayList<>();
	}
	
	@Test
	public void test_findByUser_calls_findByUser_andReturnsListOfSubscriptions() {
	User user = new User();
	when(mockSubscriptionRepository.findByUser(user)).thenReturn(expectedSubscriptions);
	List<Subscription> subscriptions = subscriptionService.findByUser(user);
	verify(mockSubscriptionRepository, times(1)).findByUser(user);
	assertEquals(expectedSubscriptions, subscriptions);
	}
	
	@Test
	public void test_findByCreator_calls_findByCreator_andReturnsListOfSubscriptions() {
	User creator = new User();
	when(mockSubscriptionRepository.findByCreator(creator)).thenReturn(expectedSubscriptions);
	List<Subscription> subscriptions = subscriptionService.findByCreator(creator);
	verify(mockSubscriptionRepository, times(1)).findByCreator(creator);
	assertEquals(expectedSubscriptions, subscriptions);
	}
	
	@Test
	public void test_subscribe_calls_save_methodOf_SubscriptionRepository() {
	Subscription subscription = new Subscription();
	subscriptionService.subscribe(subscription);
	verify(mockSubscriptionRepository, times(1)).save(subscription);
	}
	
	@Test
	public void test_unsubscribe_calls_delete_methodOf_SubscriptionRepository() {
	Subscription subscription = new Subscription();
	subscriptionService.unsubscribe(subscription);
	verify(mockSubscriptionRepository, times(1)).delete(subscription);
	}
	
	@Test
	public void test_findByUserAndCreator_calls_findByUserAndCreator_andReturnsSubscription() {
	User user = new User();
	User creator = new User();
	Subscription expectedSubscription = new Subscription();
	when(mockSubscriptionRepository.findByUserAndCreator(user, creator)).thenReturn(Optional.of(expectedSubscription));
	Subscription subscription = subscriptionService.findByUserAndCreator(user, creator);
	verify(mockSubscriptionRepository, times(1)).findByUserAndCreator(user, creator);
	assertEquals(expectedSubscription, subscription);
	}
	
	@Test
	public void test_notifySubscribers_calls_findByCreator_andSendsNotificationsToSubscribedUsers() {
	    User creator = new User();
	    User user = new User();
	    Subscription subscription = new Subscription();
	    subscription.setUser(creator);
	    subscription.setUser(user);
	    //subscription.setNotificationsOn(true);
	    List<Subscription> creatorSubscriptions = Arrays.asList(subscription);
	    
	    when(mockSubscriptionRepository.findByCreator(creator)).thenReturn(creatorSubscriptions);
	    
	    subscriptionService.notifySubscribers(creator);
	    verify(mockSubscriptionRepository, times(1)).findByCreator(creator);
	   // verify(subscriptionService, times(1)).sendNotification(subscription.getUser(), creator);
	}
	
	@Test
	public void test_findById_returnsSubscriptionWithGivenId() {
	  Subscription expectedSubscription = new Subscription();
	  expectedSubscription.setId(1L);
	  when(mockSubscriptionRepository.findById(1L)).thenReturn(Optional.of(expectedSubscription));

	  Subscription actualSubscription = subscriptionService.findById(1L);

	  assertEquals(expectedSubscription, actualSubscription);
	}

	@Test
	public void test_findById_returnsNull_whenNoSubscriptionWithGivenIdExists() {
	  when(mockSubscriptionRepository.findById(1L)).thenReturn(Optional.empty());

	  Subscription subscription = subscriptionService.findById(1L);

	  assertNull(subscription);
	}
	
	@Test
	public void test_checkSubscription_addsSubscriptionToModel_whenSubscriptionExists() {
	  User user = new User();
	  User creator = new User();
	  Subscription subscription = new Subscription();
	  Optional<Subscription> subscriptionOptional = Optional.of(subscription);
	  when(mockSubscriptionRepository.findByUserAndCreator(user, creator)).thenReturn(subscriptionOptional);
	  ModelMap model = new ModelMap();

	  subscriptionService.checkSubscription(model, user, creator);

	  assertEquals(subscription, model.get("subscription"));
	  verify(mockSubscriptionRepository, times(1)).findByUserAndCreator(user, creator);
	}
	
	@Test
	public void test_checkSubscription_doesNotAddSubscriptionToModel_whenSubscriptionDoesNotExist() {
	User user = new User();
	User creator = new User();
	Optional<Subscription> subscriptionOptional = Optional.empty();
	when(mockSubscriptionRepository.findByUserAndCreator(user, creator)).thenReturn(subscriptionOptional);
	ModelMap model = new ModelMap();

	subscriptionService.checkSubscription(model, user, creator);

	assertNull(model.get("subscription"));
	verify(mockSubscriptionRepository, times(1)).findByUserAndCreator(user, creator);
	}
	
}
