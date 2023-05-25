package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.RatingRepository;
import com.fdmgroup.WanderersBlog.service.RatingService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RatingServiceTest {
	
	@InjectMocks
	RatingService ratingService;
	
	@MockBean
	RatingRepository mockRatingRepository;
	
	@Mock
	Rating mockRating;
	
	@Mock
	User mockUser;
	
	@Test
	public void test_save_calls_save_methodOf_ratingRepository() {
		
		ratingService.save(mockRating);
		verify(mockRatingRepository, times(1)).save(mockRating);
	}
	
	@Test
	public void test_getAverageUserRating_calls_findByUser_methodOfRatingRepository() {
		double expectedRating = 3;

		Rating rating1 = new Rating();
		rating1.setValue(5);
		Rating rating2 = new Rating();
		rating2.setValue(1);
		List<Rating> ratings = new ArrayList<>();
		ratings.add(rating1);
		ratings.add(rating2);
	
		when(mockRatingRepository.findByUser(mockUser)).thenReturn(ratings);
		double returnedAverageUserRating = ratingService.getAverageUserRating(mockUser);
		verify(mockRatingRepository, times(1)).findByUser(mockUser);
		assertEquals(expectedRating, returnedAverageUserRating);
	}
	
	@Test
	public void test_findRatingByUser_calls_findByUser_methodOf_ratingRepository() {
		
		List<Rating> expectedRatings = new ArrayList<>();
		when(mockRatingRepository.findByUser(mockUser)).thenReturn(expectedRatings);
		List<Rating> returnedList = ratingService.findRatingByUser(mockUser);
		verify(mockRatingRepository, times(1)).findByUser(mockUser);
		assertEquals(expectedRatings, returnedList);
	}

}
