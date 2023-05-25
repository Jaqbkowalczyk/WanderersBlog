package com.fdmgroup.WanderersBlog.service;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.WanderersBlog.model.Comment;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;

public interface ICommentService {

	List<Story> findByUser(User user);
	void saveComment(Comment comment);

	Optional<Comment> findById(int commentId);
}
