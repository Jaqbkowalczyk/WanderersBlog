package com.fdmgroup.WanderersBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Comment;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Story> findByUser(User user);

}
