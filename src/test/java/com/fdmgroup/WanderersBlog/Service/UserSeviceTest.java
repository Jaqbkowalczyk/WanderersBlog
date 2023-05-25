package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.UserRepository;
import com.fdmgroup.WanderersBlog.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserSeviceTest {
	
	@InjectMocks
	UserService userService;
	
	@MockBean
	UserRepository mockUserRepository;
	
	@Test
	public void test_findByUsername_WithExistentUser() {
		User testUser = new User("testuser");
		when(mockUserRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
		
		User returnedUser = userService.findByUsername("testuser");
		verify(mockUserRepository, times(1)).findByUsername("testuser");
		assertEquals("testuser", returnedUser.getUsername());
	}
	
	@Test
	public void test_findByUsername_WithNonExistentUser() {

		User defaultUser = userService.findByUsername("non-existent user");
        verify(mockUserRepository).findByUsername("non-existent user");
        assertEquals("default user", defaultUser.getUsername());
	}
	
	@Test
	public void test_findByUserId_WithExistentUser() {
		int id = 5;
		User testUser = new User("testUser");
		Mockito.when(mockUserRepository.findByUserId(id)).thenReturn(Optional.of(testUser));
		
		User user = userService.findByUserId(id);
		verify(mockUserRepository, times(1)).findByUserId(id);
		assertEquals("testUser", user.getUsername());
	}
	
	@Test
	public void test_findByUserId_WithNonExistentUser() {
		int id = 5;

		User defaultUser = userService.findByUserId(id);
		verify(mockUserRepository, times(1)).findByUserId(id);
		assertEquals("default user", defaultUser.getUsername());
	}
	
}
