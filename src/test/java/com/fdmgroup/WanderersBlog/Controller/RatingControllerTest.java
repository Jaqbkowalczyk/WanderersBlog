package com.fdmgroup.WanderersBlog.Controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fdmgroup.WanderersBlog.WanderersBlogApplication;
import com.fdmgroup.WanderersBlog.controller.RatingController;
import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.RatingService;
import com.fdmgroup.WanderersBlog.service.UserService;

@SpringBootTest(classes = {RatingController.class})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = WanderersBlogApplication.class)
public class RatingControllerTest {
	
	@MockBean
	RatingService mockRatingService;
	
	@MockBean
	LoginService mockLoginService;
	
	@MockBean
	UserService mockUserService;
	
	@Autowired
	private MockMvc mockMvc;
	

	@Test
	@WithMockUser
	public void test_rate_calls_save_methodOf_RatingService_AndReturn_redirectUserProfileUsername() throws Exception {
		
		User user = new User();
		user.setUsername("user");
		User viewUser = new User();
		viewUser.setUsername("viewuser");
		
		when(mockUserService.findByUsername("viewuser")).thenReturn(viewUser);
		when(mockLoginService.getLoggedUser()).thenReturn(user);
	
		Rating rating = new Rating();
		rating.setComment("Test comment");
		rating.setRater(user);
		rating.setUser(viewUser);
		rating.setValue(5);
		
		MvcResult result = mockMvc.perform(post("/rate/viewuser").param("value", "5")
				.param("comment", "Test comment")
				.param("username", "viewuser"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/userProfile/viewuser"))
				.andReturn();
	
	}
}
