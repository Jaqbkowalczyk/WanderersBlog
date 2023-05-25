package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Comment;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.CommentRepository;
import com.fdmgroup.WanderersBlog.service.CommentService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CommentServiceTest {
	
	@InjectMocks
	CommentService commentService;
	
	@MockBean
	CommentRepository mockCommentRepository;
	
	@Mock
	Comment mockComment;
	
	@Mock
	User mockUser;
	
	@Test
	public void test_findByUser_calls_findByUser_methodOf_commentRepository() {
		List<Story> expectedStories = new ArrayList<>();
		when(mockCommentRepository.findByUser(mockUser)).thenReturn(expectedStories);
		List<Story> returnedStories = commentService.findByUser(mockUser);
		verify(mockCommentRepository, times(1)).findByUser(mockUser);
		assertEquals(expectedStories, returnedStories);
	}
	
	@Test
	public void test_saveComment_calls_save_methodOf_commentRepository() {
		commentService.saveComment(mockComment);
		verify(mockCommentRepository, times(1)).save(mockComment);	
	}
	
	@Test
	public void test_findById_calls_findById_methodOf_commentRepository() {
		int id = 1;
		when(mockCommentRepository.findById(id)).thenReturn(Optional.of(mockComment));
		Optional<Comment> returnedComment = commentService.findById(id);
		verify(mockCommentRepository, times(1)).findById(id);
		assertEquals(Optional.of(mockComment), returnedComment);
	}	
}
