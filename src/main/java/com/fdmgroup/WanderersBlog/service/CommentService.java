package com.fdmgroup.WanderersBlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Comment;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.CommentRepository;

@Service
public class CommentService implements ICommentService {
	@Autowired
	private CommentRepository repo;

	@Override
	public List<Story> findByUser(User user) {

		return repo.findByUser(user);
	}

	@Override
	public void saveComment(Comment comment) {
		repo.save(comment);
	}

	@Override
	public Optional<Comment> findById(int commentId) {

		return repo.findById(commentId);
	}
}
