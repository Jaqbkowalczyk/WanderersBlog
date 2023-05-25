package com.fdmgroup.WanderersBlog.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.WanderersBlog.WanderersBlogApplication;
import com.fdmgroup.WanderersBlog.controller.MainController;
import com.fdmgroup.WanderersBlog.service.LoginService;

@SpringBootTest(classes = {MainController.class})
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = WanderersBlogApplication.class)
public class MainControllerTest {
	
	@MockBean
	private LoginService mockLoginService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@WithMockUser
	public void test_goToIndex_returnsIndex_statusOk() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	
	@Test
	@WithMockUser
	public void test_goToCategory_returnsShowPostsOfCategory_statusOk() throws Exception {
		mockMvc.perform(get("/Category")).andExpect(status().isOk())
		.andExpect(view().name("show-posts-of-category"));
	}
	
	@Test
	@WithMockUser
	public void test_goToFindCompany_returnsFindCompany_statusOk() throws Exception {
		mockMvc.perform(get("/FindCompany")).andExpect(status().isOk())
		.andExpect(view().name("find-company"));
	}

}
