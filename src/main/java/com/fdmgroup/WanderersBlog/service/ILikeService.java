package com.fdmgroup.WanderersBlog.service;

import com.fdmgroup.WanderersBlog.model.Like;

public interface ILikeService {
	public Like findByUserIdAndStoryId(int userId, int storyId);

	public void save(Like like);

	public void delete(Like like);
}
