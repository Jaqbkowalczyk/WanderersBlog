package com.fdmgroup.WanderersBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.User;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByUser(User user);
}
