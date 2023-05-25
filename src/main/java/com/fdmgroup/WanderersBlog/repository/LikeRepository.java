package com.fdmgroup.WanderersBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>{
	Like findByUserIdAndStoryId(int userId, int storyId);
}
