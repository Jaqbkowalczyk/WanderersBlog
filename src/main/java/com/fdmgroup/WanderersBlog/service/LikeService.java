package com.fdmgroup.WanderersBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Like;
import com.fdmgroup.WanderersBlog.repository.LikeRepository;

@Service
public class LikeService implements ILikeService {
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Override
	public Like findByUserIdAndStoryId(int userId, int storyId) {
		return likeRepository.findByUserIdAndStoryId(userId, storyId);
	}
	
	public void save(Like like) {
		likeRepository.save(like);
	}
	
	public void delete(Like like) {
		likeRepository.delete(like);
	}
}
