package com.fdmgroup.WanderersBlog.Controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.WanderersBlog.WanderersBlogApplication;
import com.fdmgroup.WanderersBlog.controller.ProfileController;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;
import com.fdmgroup.WanderersBlog.security.UserPrincipal;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.OfferService;
import com.fdmgroup.WanderersBlog.service.RatingService;
import com.fdmgroup.WanderersBlog.service.StoryService;
import com.fdmgroup.WanderersBlog.service.UserService;

@SpringBootTest(classes = {ProfileController.class})
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = WanderersBlogApplication.class)
public class ProfileControllerTest {
	
	@MockBean
	private UserService mockUserService;
	
	@MockBean
	private LoginService mockLogService;
	
	@MockBean
	private StoryService mockStoryService;
	
	@MockBean
	private RatingService mockRatingService;
	
	@MockBean
	private OfferService mockOfferService;
	
	@MockBean 
	private DefaultUserDetailsService mockDefaultUserDetailsService;

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	User mockUser;
	
	@Test
	@WithMockUser
	public void testGoToShowUserProfile_shouldReturnUserProfilePage_addsAttributes() throws Exception {
		

		String username = "testUser";
		User user = new User("testUser");
		List<Story> expectedUserStories = new ArrayList<>();
		List<Offer> expectedUserOffers = new ArrayList<>();
		List<Rating> expectedRatings = new ArrayList<>();
		
		when(mockUserService.findByUsername(username)).thenReturn(user);
		when(mockStoryService.findByUser(user)).thenReturn(expectedUserStories);
		when(mockOfferService.findByUser(user)).thenReturn(expectedUserOffers);
		when(mockRatingService.findRatingByUser(user)).thenReturn(expectedRatings);
		when(mockRatingService.getAverageUserRating(user)).thenReturn(0.0);
		when(mockLogService.getLoggedUser()).thenReturn(mockUser);
		
		
		mockMvc.perform(get("/userProfile/testUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("user-profile"))
		.andExpect(model().attribute("viewUser", user))
		.andExpect(model().attribute("user", mockUser))
		.andExpect(model().attribute("ratings", expectedRatings))
		.andExpect(model().attribute("userRating", 0.0))
		.andExpect(model().attribute("offers", expectedUserOffers))
		.andExpect(model().attribute("stories", expectedUserStories));
	}
	
	@Test
	@WithMockUser
	public void testGoToEditUserDetails_shouldReturnEditUserDetailsPage_addsAttributes() throws Exception {
		User mockUser = new User("testUser");

		when(mockLogService.getLoggedUser()).thenReturn(mockUser);

		mockMvc.perform(get("/editUserDetails"))
		.andExpect(status().isOk())
		.andExpect(view().name("edit-user-details"))
		.andExpect(model().attribute("user", mockUser));
	
	}
	
	@Test
	public void testEditUserDetails_shouldUpdateUserDetailsAndRedirectToProfilePage() throws Exception {
		String firstName = "John";
		String surName = "Doe";
		String email = "john.doe@example.com";
		UserPrincipal userPrincipal = new UserPrincipal(new User("testUser"));
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		when(mockDefaultUserDetailsService.loadUserByUsername("testUser")).thenReturn(userPrincipal);
		
		User updatedUser = new User("testUser");
		updatedUser.setFirstName("John");
		updatedUser.setSurName("Doe");
		updatedUser.setEmail("john.doe@example.com");
		
		mockMvc.perform(post("/editUserDetails")
				.param("firstName", firstName)
				.param("surName", surName)
				.param("email", email)
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("user-profile"))
			.andExpect(model().attribute("userName", "testUser"))
			.andExpect(model().attribute("userFristName", "John"))
			.andExpect(model().attribute("userSurName", "Doe"))
			.andExpect(model().attribute("userEmail", "john.doe@example.com"));
		
		verify(mockDefaultUserDetailsService).saveUser(userPrincipal.getUser());
	}
	
	
}
