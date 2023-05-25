package com.fdmgroup.WanderersBlog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.StoryRepository;

@Service
public class StoryService implements IStoryService {
	@Autowired
	private StoryRepository repo;

	@Override
	public List<Story> findByTitle(String title) {

		return repo.findByTitle(title);
	}

	@Override
	public List<Story> findByCategory(String category) {

		return repo.findByCategory(category);
	}

	@Override
	public List<Story> findByLocation(String location) {

		return repo.findByLocation(location);
	}

	@Override
	public List<Story> findByUser(User user) {

		return repo.findByUser(user);
	}

	@Override
	public List<Story> findAllStories() {
		return repo.findAll();
	}

	@Override
	public List<Story> filterStories(String filter) {
		String[] filtersArray = filter.split(" ");
		List<List<Story>> results = new ArrayList<List<Story>>();

		for (String filters : filtersArray) {
			List<Story> filteredByTitle = repo.findByTitleIgnoreCaseContaining(filters);
			List<Story> filteredByCategory = repo.findByCategoryIgnoreCaseContaining(filters);
			List<Story> filteredByDestination = repo.findByLocationIgnoreCaseContaining(filters);

			List<Story> filteredStories = new ArrayList<>();
			Stream.of(filteredByTitle, filteredByCategory, filteredByDestination).forEach(filteredStories::addAll);
			results.add(filteredStories);
		}

		List<Story> finalFilteredStories = new ArrayList<Story>();
		finalFilteredStories.addAll(results.get(0));
		for (ListIterator<List<Story>> iter = results.listIterator(0); iter.hasNext();) {
			finalFilteredStories.retainAll(iter.next());
		}
		//remove duplicates
		return finalFilteredStories.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public void saveStory(Story story) {
		repo.save(story);
	}

	@Override
	public Optional<Story> findById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Story> findByCategoryIgnoreCase(String category) {
		return repo.findByCategoryIgnoreCase(category);
	}
	@Override
	public List<Story> getAllStoriesSortedByDate(){
		List<Story> sortedStories = repo.findAll();
		Collections.sort(sortedStories, (s1, s2) -> s2.getDate().compareTo(s1.getDate()));
		return sortedStories ;
	}

	@Override
	public List<Story> findByLocationIgnoreCase(String location) {
		
		return repo.findByLocationIgnoreCase(location) ;
	}
	
	@Override
	public List<Story> findAllStoriesOrderByLikes() {
		return repo.findAllStoriesOrderByLikes();
	}
	
	
	@Override
	public List<Story> recommendStories(Story currentStory) {
		List<Story> stories = repo.findByCategory(currentStory.getCategory());
		stories.remove(currentStory);

		int maxStories = 3;
		int numberOfStories = stories.size() < maxStories ? stories.size() : maxStories;

		Random random = new Random();
		List<Story> recommendedStories = new ArrayList<>();
		for (int i = 0; i < numberOfStories; i++) {
		  int randomIndex = random.nextInt(stories.size());
		  recommendedStories.add(stories.get(randomIndex));
		  stories.remove(randomIndex);
		}
		
		return recommendedStories;
	}
	
	
}
