package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.StoryRepository;
import com.fdmgroup.WanderersBlog.service.StoryService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StoryServiceTest {

	@InjectMocks
	StoryService storyService;
	
	@MockBean
	private StoryRepository mockStoryRepository;
	
	@Mock
	User mockUser;
	
	@Mock
	Story mockStory;
	
	List<Story> expectedList;
	
	@BeforeEach
	public void setUp() {
		expectedList = new ArrayList<>();
	}
	
	@Test
	public void test_findByTitle_calls_findByTitle_andReturnsListOfStories() {
		when(mockStoryRepository.findByTitle("title")).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByTitle("title");
		verify(mockStoryRepository, times (1)).findByTitle("title");
		assertEquals(expectedList, returnedList);
	}
	
	@Test
	public void test_findByCategory_calls_findByCategory_andReturnsListOfStories() {
		when(mockStoryRepository.findByCategory("category")).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByCategory("category");
		verify(mockStoryRepository, times (1)).findByCategory("category");
		assertEquals(expectedList, returnedList);
	}
	
	@Test
	public void test_findByLocation_calls_findByLocation_andReturnsListOfStories() {
		when(mockStoryRepository.findByLocation("location")).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByLocation("location");
		verify(mockStoryRepository, times (1)).findByLocation("location");
		assertEquals(expectedList, returnedList);
	}

	
	@Test
	public void test_findByUser_calls_findByUser_andReturnsListOfStories() {
		when(mockStoryRepository.findByUser(mockUser)).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByUser(mockUser);
		verify(mockStoryRepository, times (1)).findByUser(mockUser);
		assertEquals(expectedList, returnedList);
	}
	
	@Test
	public void test_findAllStories_calls_findAll_andReturnsListOfStoriesy() {
		List<Story> returnedList = storyService.findAllStories();
		verify(mockStoryRepository, times (1)).findAll();
		assertEquals(expectedList, returnedList);
	}
	
	
	@Test
	public void testFilterStories() {
		String filter = "test filter";
		String filter1 = "test";
		String filter2 = "filter";
		
		storyService.filterStories(filter);
		
		verify(mockStoryRepository, times (1)).findByTitleIgnoreCaseContaining(filter1);
		verify(mockStoryRepository, times (1)).findByTitleIgnoreCaseContaining(filter2);
		verify(mockStoryRepository, times (1)).findByCategoryIgnoreCaseContaining(filter1);
		verify(mockStoryRepository, times (1)).findByCategoryIgnoreCaseContaining(filter2);		
		verify(mockStoryRepository, times (1)).findByLocationIgnoreCaseContaining(filter1);
		verify(mockStoryRepository, times (1)).findByLocationIgnoreCaseContaining(filter2);	

	}


	@Test
	public void test_saveStory_calls_save_MethodOf_StoryRepository() {
		storyService.saveStory(mockStory);
		verify(mockStoryRepository, times (1)).save(mockStory);
	}
	
	@Test
	public void test_findById_calls_findById_andReturnsOptionalStory() {
		int id = 5;
		when(mockStoryRepository.findById(id)).thenReturn(Optional.of(mockStory));
		Optional<Story> returnedStory = storyService.findById(id);
		verify(mockStoryRepository, times (1)).findById(id);
		assertEquals(Optional.of(mockStory), returnedStory);
	}
	
	@Test
	public void test_findByCategoryIgnoreCase_calls_findByCategoryIgnoreCase_andReturnsListOfStories() {
		
		when(mockStoryRepository.findByCategoryIgnoreCase("category")).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByCategoryIgnoreCase("category");
		verify(mockStoryRepository, times (1)).findByCategoryIgnoreCase("category");
		assertEquals(expectedList, returnedList);
	}
	
	@Test
	public void test_findByLocationIgnoreCase_calls_findByLocationIgnoreCase_andReturnsListOfStories() {
		when(mockStoryRepository.findByLocationIgnoreCase("location")).thenReturn(expectedList);
		List<Story> returnedList = storyService.findByLocationIgnoreCase("location");
		verify(mockStoryRepository, times (1)).findByLocationIgnoreCase("location");
		assertEquals(expectedList, returnedList);
	}
	
}
