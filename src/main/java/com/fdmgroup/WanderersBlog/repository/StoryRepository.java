package com.fdmgroup.WanderersBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

	List<Story> findByTitle(String title);
	List<Story> findByUser(User user);
	List<Story> findByLocation(String location);
	List<Story> findByCategory(String category);
	List<Story> findByCategoryIgnoreCase(String category);
	List<Story> findByTitleIgnoreCaseContaining(String filters);
	List<Story> findByLocationIgnoreCaseContaining(String filters);
	List<Story> findByCategoryIgnoreCaseContaining(String filters);
	List<Story> findByLocationIgnoreCase(String location);
	
	@Query("SELECT s FROM Story s ORDER BY s.likes DESC")
	List<Story> findAllStoriesOrderByLikes();

}
