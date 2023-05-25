package com.fdmgroup.WanderersBlog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;
	
	public void save(Rating rating) {
		ratingRepository.save(rating);
	}
	
	public Double getAverageUserRating(User user) {
		List<Rating> ratings = ratingRepository.findByUser(user);
		List<Rating> filteredRatings = ratings.stream()
                .filter(rating -> rating.getValue() != 0)
                .collect(Collectors.toList());
		//return ratings.stream().map
		return filteredRatings.stream().mapToInt(Rating::getValue).average().orElse(0.0);
	}
	
	public List<Rating> findRatingByUser(User user){
		List<Rating> ratings = ratingRepository.findByUser(user);
		return ratings;
	}
	
}
