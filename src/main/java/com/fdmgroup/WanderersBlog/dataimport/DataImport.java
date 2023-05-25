package com.fdmgroup.WanderersBlog.dataimport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.MessageRepository;
import com.fdmgroup.WanderersBlog.repository.OfferRepository;
import com.fdmgroup.WanderersBlog.repository.StoryRepository;
import com.fdmgroup.WanderersBlog.repository.UserRepository;
import com.fdmgroup.WanderersBlog.service.ConversationRepository;
import com.fdmgroup.WanderersBlog.service.IMessageService;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Lazy
	private PasswordEncoder encoder;

	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private IMessageService messageService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!userRepository.findByUsername("admin").isPresent()) {

			// Dummy users
			User wanderer = new User("Wanderer", encoder.encode("123"), "wanderer@wanderers.pl", "Wanderer", "Blog");
			userRepository.save(wanderer);
			
			User admin = new User("admin", encoder.encode("123"), "admin@admin.pl", "John", "Doe");
			userRepository.save(admin);

			User user1 = new User("kuba", encoder.encode("123"), "jakub.kowalczyk@example.com", "Jakub", "Kowalczyk");
			userRepository.save(user1);

			User user2 = new User("anna", encoder.encode("123"), "anna.peretiatko@example.com", "Anna", "Peretiatko");
			userRepository.save(user2);

			User user3 = new User("stan", encoder.encode("123"), "stanislaw.swiatek@example.com", "Stanislaw",
					"Swiatek");
			userRepository.save(user3);

			User user4 = new User("cysia", encoder.encode("123"), "sylwia.lampart-wieckowska@example.com", "Sylwia",
					"Lampart-Wieckowska");
			userRepository.save(user4);

			User user5 = new User("ola", encoder.encode("123"), "aleksandra.wlazlo@example.com", "Aleksandra",
					"Wlazlo");
			userRepository.save(user5);

			// Add dummy products
			String[] titles = { "Discovering the Beauty of North America", "Budget-Friendly Adventure in South America",
					"Exploring the Culture of Africa", "A Foodie's Guide to Europe", "Family-Friendly Oceania",
					"Group Travel in Asia", "The Thrill of Hitchhiking in the Middle East",
					"Luxury Travel in the Arctic", "Nightlife in Europe: A Guide",
					"Solo Travel in South America: My Experience", "Trekking the Himalayas: A Journey to Remember",
					"The Best Beaches in the Caribbean", "The Hidden Gems of Central America",
					"Discovering the Architecture of Eastern Europe", "Surviving a Road Trip in Australia",
					"Exploring the Temples of Southeast Asia", "The Ultimate Safari Experience in Kenya",
					"Camping in the Wilds of North America", "The History and Culture of Mediterranean Europe",
					"The Natural Wonders of South America" };

			String[] descriptions = {
					"North America offers a vast range of landscapes and cultures to explore, from bustling cities to breathtaking national parks. Whether you're looking for adventure or relaxation, there's something for everyone here. In this post, we'll cover some of the best places to visit, things to do, and how to make the most of your trip.",
					"South America is a budget traveler's dream, with its diverse cultures, rich history, and incredible natural beauty. In this post, we'll cover some of the best places to visit, things to do, and tips for traveling on a budget in this amazing continent.",
					"Africa is a continent full of culture, history, and natural beauty. From the bustling cities to the peaceful deserts, there's something for everyone here. In this post, we'll cover some of the best places to visit and things to do, as well as some tips for experiencing the culture of Africa.",
					"Europe is a foodie's paradise, with its diverse range of cuisines and delicious delicacies. In this post, we'll cover some of the best places to eat, drink, and explore the local food scene. Whether you're a fan of traditional dishes or exotic flavors, you're sure to find something you love in Europe.",
					"Oceania is a great destination for families, with its stunning landscapes, friendly locals, and abundance of outdoor activities. In this post, we'll cover some of the best places to visit, things to do, and tips for traveling with kids in this beautiful region.",
					"Asia is a great destination for group travel, with its diverse cultures, rich history, and amazing food. In this post, we'll cover some of the best places to visit, things to do, and tips for traveling with a group in this amazing continent.",
					"Hitchhiking in the Middle East is a unique and thrilling way to travel, offering the opportunity to meet local people and experience the culture up close. In this post, we'll cover some of the best places to hitchhike, tips for staying safe, and how to make the most of your journey.",
					"The Arctic is a destination for luxury travel, offering breathtaking landscapes and unrivaled beauty. In this post, we'll cover some of the best places to visit, things to do, and tips for experiencing the ultimate luxury travel in this incredible region.",
					"Europe is renowned for its nightlife, with its vibrant cities and diverse range of experiences. In this post, we'll cover some of the best places to party, drink, and dance, as well as tips for experiencing the nightlife in Europe.",
					"South America is home to one of the world's largest and most diverse rainforests, the Amazon. This magical place is filled with an abundance of wildlife, lush vegetation, and indigenous communities. Join us as we take a journey deep into the heart of the Amazon to explore its beauty and learn about the challenges it faces. In this blog post, we will share tips on how to travel to the Amazon on a budget, where to stay, and what to do while you're there. Don't miss out on the opportunity to experience one of the world's most unique and breathtaking destinations!",
					"Join us as we embark on an unforgettable journey through the majestic Himalayas. In this blog post, we'll share tips on how to prepare for the trek, what to bring, and the best routes to take. Experience the stunning scenery and unique culture of this amazing region.",
					"The Caribbean is home to some of the world's most beautiful beaches, with crystal clear waters and soft, white sand. In this post, we'll cover some of the best beaches to visit, things to do, and tips for traveling in this tropical paradise.",
					"Central America is full of hidden gems, from breathtaking waterfalls to vibrant culture. In this post, we'll cover some of the best places to visit and things to do, as well as some tips for discovering the hidden beauty of this region.",
					"Eastern Europe is renowned for its rich history and stunning architecture, from towering cathedrals to grand palaces. In this post, we'll cover some of the best places to visit and explore the history and culture of this fascinating region.",
					"Australia is a land of endless possibilities, from rugged deserts to stunning beaches. In this post, we'll cover tips for surviving a road trip in this vast country, including the best routes to take and things to see along the way.",
					"Southeast Asia is home to some of the world's most breathtaking temples, from the ruins of Angkor Wat to the grandeur of Bali. In this post, we'll cover some of the best temples to visit and tips for exploring this fascinating region.",
					"Kenya is a wildlife lover's paradise, with its abundance of stunning landscapes and incredible wildlife. In this post, we'll cover the best places to go on safari, what to see, and tips for experiencing the ultimate safari experience.",
					"North America is home to some of the world's most stunning wilderness, from the mountains to the forests. In this post, we'll cover some of the best places to go camping, tips for surviving in the wilds, and what to bring on your trip.",
					"Mediterranean Europe is rich in history and culture, with its stunning architecture and delicious cuisine. In this post, we'll cover some of the best places to visit and explore the history and culture of this fascinating region.",
					"South America is home to some of the world's most breathtaking natural wonders, from the Amazon rainforest to the Andes Mountains. In this post, we'll cover the best places to visit and what to see, as well as tips for experiencing the natural beauty of this incredible region." };

			String[] categories = { "Adventure", "Budget Travel", "Culture", "Food", "Family Travel", "Group Travel",
					"Hitchhiking", "Luxury Travel", "Nightlife", "Solo Travel", "Adventure", "Family Travel",
					"Adventure", "Culture", "Adventure", "Culture", "Adventure", "Family Travel", "Culture",
					"Adventure" };

			String[] locations = { "North America", "South America", "Africa", "Europe", "Oceania", "Asia",
					"Middle East", "Arctic", "Europe", "South America", "Asia", "South America", "North America",
					"Europe", "Oceania", "Asia", "Africa", "North America", "Europe", "South America"};
			Date[] dates = { new Date(121, 5, 20), new Date(121, 7, 11), new Date(121, 8, 29), new Date(121, 9, 12),
					new Date(121, 10, 07), new Date(121, 11, 21), new Date(121, 12, 15), new Date(122, 1, 05),
					new Date(122, 2, 01), new Date(122, 2, 06), new Date(122, 3, 01), new Date(122, 4, 15),
					new Date(122, 5, 05), new Date(122, 6, 01), new Date(122, 7, 11), new Date(122, 8, 29),
					new Date(122, 9, 12), new Date(122, 10, 07), new Date(122, 11, 21), new Date(122, 12, 15) };

			User[] authors = { user5, user1, user2, user3, user4, user5, user1, user2, user3, user4, user5, user1,
					user2, user3, user4, user5, user1, user2, user3, user4 };

			List<Story> stories = new ArrayList<>();
			List<List<String>> listOfLists = new ArrayList<>();
			Random rand = new Random();
			for (int i = 0; i < 20; i++) {
				List<String> list = new ArrayList<>();
				list.add("/img/" + i + ".jpg");
				listOfLists.add(list);
			}
			for (int i = 0; i < 20; i++) {
				Story story = new Story();
				story.setTitle(titles[i]);
				story.setDescription(descriptions[i]);
				story.setCategory(categories[i]);
				story.setLocation(locations[i]);
				story.setDate(dates[i]);
				story.setUser(authors[i]);
				story.addImage(listOfLists.get(i).get(0));
				int x = rand.nextInt(10);
				story.setLikes(x);
				stories.add(story);
			}

			storyRepository.saveAll(stories);

			// Create dummy offers
			String[] titlesOffer = { "Adventure in the Amazon", "Explore Europe Together",
					"Island Hopping in Southeast Asia", "Discover South America", "African Safari Experience",
					"Road Trip Across the US", "Trekking the Himalayas", "Scuba Diving in the Great Barrier Reef",
					"Discover New Zealand", "Cruise the Caribbean" };

			String[] descriptionsOffer = {
					"Join me for a once in a lifetime adventure in the Amazon rainforest. I am looking for someone who loves nature and adventure. Let's explore the Amazonian jungle together!",
					"I am planning a trip to Europe this summer and I am looking for a travel companion. Let's visit countries like France, Italy, Spain, and Germany together!",
					"Are you ready to island hop with me in Southeast Asia? I am looking for someone who loves adventure, good food, and a tropical paradise. Let's explore countries like Thailand, Vietnam, and Cambodia!",
					"Join me on a journey through South America! I am looking for someone who loves exploring new cultures, trying new foods, and having new experiences. Let's visit countries like Brazil, Argentina, and Peru!",
					"Join me for a thrilling African Safari Experience! I am looking for someone who loves wildlife and adventure. Let's visit countries like Kenya, Tanzania, and South Africa!",
					"I am planning a road trip across the US and I am looking for a travel companion. Let's visit states like California, Nevada, Arizona, and Texas!",
					"Are you up for a challenge? I am planning to trek the Himalayas and I am looking for someone who loves adventure and the great outdoors. Let's explore countries like Nepal and Tibet!",
					"Join me for a scuba diving adventure in the Great Barrier Reef. I am looking for someone who loves the underwater world. Let's explore the beautiful waters of Australia!",
					"Join me on a journey to discover New Zealand! I am looking for someone who loves adventure, the great outdoors, and stunning scenery. Let's explore the North and South Islands!",
					"Join me on a cruise through the Caribbean! I am looking for someone who loves sun, sea, and adventure. Let's visit countries like the Bahamas, Jamaica, and the Dominican Republic!" };

			String[] locationsOffer = { "South America", "Europe", "Asia", "South America", "Africa", "North America",
					"Asia", "Oceania", "Oceania", "South America" };

			Date[] datesOffer = { new Date(122, 7, 15), 
				    new Date(122, 6, 1), 
				    new Date(122, 8, 20), 
				    new Date(122, 10, 1), 
				    new Date(122, 5, 15), 
				    new Date(122, 4, 30), 
				    new Date(122, 7, 10), 
				    new Date(122, 6, 15), 
				    new Date(122, 8, 1), 
				    new Date(122, 11, 20)  };

			Date[] startDates = { new Date(122, 9, 1), new Date(122, 7, 15), new Date(122, 10, 1),
					new Date(122, 11, 20), new Date(122, 6, 30), new Date(122, 5, 20), new Date(122, 8, 20),
					new Date(122, 7, 25), new Date(122, 9, 25), new Date(121, 12, 30) };
			Date[] endDates = { new Date(122, 9, 20), new Date(122, 8, 20), new Date(122, 11, 1),
					new Date(122, 12, 20), new Date(122, 7, 20), new Date(122, 6, 10), new Date(122, 9, 20),
					new Date(122, 8, 20), new Date(122, 10, 20), new Date(122, 1, 10) };

			Integer[] howManyLookingFor = { 2, 8, 5, 7, 2, 1, 9, 3, 10, 4 };
			User[] users = { user1, user2, user3, user4, user5, user5, user4, user3, user2, user1 };
			List<Offer> offers = new ArrayList<>();
			List<List<String>> listOfimages = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				List<String> list = new ArrayList<>();
				list.add("/img/offer/" + i + ".jpg");
				listOfimages.add(list);
			}
			for (int i = 0; i < 10; i++) {
				
				Offer offer = new Offer(users[i], titlesOffer[i], descriptionsOffer[i], locationsOffer[i], datesOffer[i],
						startDates[i], endDates[i], howManyLookingFor[i]);
				offer.setImages(listOfimages.get(i));
				offers.add(offer);
				
				
				
				
			}
			offerRepository.saveAll(offers);
			
		}

	}

}
