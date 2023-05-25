package com.fdmgroup.WanderersBlog.service;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;

public interface IStoryService {
	List<Story> findByTitle(String title);

	List<Story> findByUser(User user);

	List<Story> findByLocation(String location);
	
	List<Story> filterStories(String filter);
	
	List<Story> findAllStories();

	List<Story> findByCategory(String category);
	
	List<Story> findByCategoryIgnoreCase(String category);

	void saveStory(Story story);

	Optional<Story> findById(int id);

	List<Story> getAllStoriesSortedByDate();

	List<Story> findByLocationIgnoreCase(String location);


	List<Story> findAllStoriesOrderByLikes();

	List<Story> recommendStories(Story currentStory);

}
