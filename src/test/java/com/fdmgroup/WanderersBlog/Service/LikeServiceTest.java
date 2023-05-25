package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Like;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.LikeRepository;
import com.fdmgroup.WanderersBlog.service.LikeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class LikeServiceTest {
	
	@InjectMocks
	LikeService likeService;
	
	@MockBean
	LikeRepository mockLikeRepository;
	
	@Mock
	User mockUser;
	
	@Mock
	Story mockStory;
	
	@Mock
	Like mockLike;
	
	@Test
	public void test_findByUserIdAndStoryId_calls_findByUserIdAndStoryId_MethodOf_LikeRepository() {
		
		when(mockLikeRepository.findByUserIdAndStoryId(1, 1)).thenReturn(mockLike);
		Like returnedLike = likeService.findByUserIdAndStoryId(1, 1);
		verify(mockLikeRepository, times(1)).findByUserIdAndStoryId(1, 1);
		assertEquals(mockLike, returnedLike);
	}
	
	@Test
	public void test_save_calls_save_MethodOf_LikeRepository() {
		likeService.save(mockLike);
		verify(mockLikeRepository, times(1)).save(mockLike);
	}
	
	@Test
	public void test_delete_calls_delete_MethodOf_LikeRepository() {
		likeService.delete(mockLike);
		verify(mockLikeRepository, times(1)).delete(mockLike);
	}
}
