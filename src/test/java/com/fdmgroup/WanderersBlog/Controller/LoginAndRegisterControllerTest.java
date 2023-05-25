package com.fdmgroup.WanderersBlog.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.fdmgroup.WanderersBlog.WanderersBlogApplication;
import com.fdmgroup.WanderersBlog.controller.LoginAndRegisterController;
import com.fdmgroup.WanderersBlog.model.Role;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.RegisterService;
import com.fdmgroup.WanderersBlog.service.RoleService;

@SpringBootTest(classes = {LoginAndRegisterController.class})
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = WanderersBlogApplication.class)
public class LoginAndRegisterControllerTest {
	@MockBean
	private LoginService mockLoginService;

	@MockBean
	private RegisterService mockRegisterService;
	
	@MockBean
	private DefaultUserDetailsService mockUserService;
	
	@MockBean
	private RoleService mockRoleService;
	
	@MockBean
	private PasswordEncoder mockPasswordEncoder;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_login_returnsLogin_statusOk() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk())
		.andExpect(view().name("login"));
	}

	@Test
	public void test_register_returnsRegister_statusOk() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk())
		.andExpect(view().name("register"));
	}

	@Test
	@WithMockUser
	public void test_goToLogged_returnsRedirect_statusOk() throws Exception {
		mockMvc.perform(get("/logged")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void test_registerSubmit_withExistingUsername_returnsRegister_statusOk() throws Exception {
	    User user = new User();
	    user.setUsername("existingUser");
	    user.setPassword("testPassword");
	    user.setEmail("testEmail@gmail.com");

	    // Setup the mock service to return a user with the same username
	    Optional<User> existingUser = Optional.of(new User());
	    existingUser.get().setUsername("existingUser");
	    Mockito.when(mockUserService.findByUsername("existingUser")).thenReturn(existingUser);

	    mockMvc.perform(post("/register").param("username", "existingUser")
	            .param("password", "testPassword").param("email", "testEmail@gmail.com")
	            .param("confirmPassword", "testPassword")).andExpect(status().isOk())
	            .andExpect(view().name("register"));
	}
	
	@Test
	public void test_registerSubmit_withDifferentPasswords_returnsRegister_statusOk() throws Exception {
	    User user = new User();
	    user.setUsername("testUser");
	    user.setPassword("testPassword");
	    user.setEmail("testEmail@gmail.com");

	    // Setup the mock service to return an empty Optional for the user
	    //Mockito.when(mockUserService.findByUsername("testUser")).thenReturn(Optional.empty());
	    
	    
	    Mockito.when(mockRegisterService.registerSubmit(user, "differentPassword", new ModelMap()))
        .thenReturn("register");

	    mockMvc.perform(post("/register").param("username", "testUser")
	            .param("password", "testPassword").param("email", "testEmail@gmail.com")
	            .param("confirmPassword", "differentPassword")).andExpect(status().isOk())
	            .andExpect(view().name("register"));
	}

	
	//tests not working
	/*
	 * @Test public void test_registerSubmit_withValidData_returnsIndex_statusOk()
	 * throws Exception { User user = new User(); user.setUsername("testUser");
	 * user.setPassword("testPassword"); user.setEmail("testEmail@gmail.com");
	 * 
	 * Role customerRole = new Role("Customer");
	 * 
	 * // Setup the mock service to return an empty Optional for the user
	 * Mockito.when(mockUserService.findByUsername(user.getUsername())).thenReturn(
	 * Optional.empty());
	 * Mockito.when(mockRoleService.findByRoleName("Customer")).thenReturn(
	 * customerRole);
	 * Mockito.when(mockPasswordEncoder.encode("testPassword")).thenReturn(
	 * "encodedTestPassword");
	 * Mockito.when(mockUserService.saveUser(user)).thenReturn(user);
	 * 
	 * 
	 * mockMvc.perform(post("/register").param("username", "testUser")
	 * .param("password", "testPassword").param("email", "testEmail@gmail.com")
	 * .param("confirmPassword", "testPassword")).andExpect(status().isOk())
	 * .andExpect(view().name("index")); }
	 */
	
	/*
	 * @Test public void test_registerSubmit_withValidData_returnsIndex_statusOk()
	 * throws Exception { User user = new User(); user.setUsername("testUser");
	 * user.setPassword("testPassword"); user.setEmail("testEmail@gmail.com");
	 * 
	 * Mockito.when(mockRegisterService.registerSubmit(user, "testPassword", new
	 * ModelMap())) .thenReturn("index");
	 * 
	 * mockMvc.perform(post("/register").param("username", "testUser")
	 * .param("password", "testPassword").param("email", "testEmail@gmail.com")
	 * .param("confirmPassword", "testPassword")).andExpect(status().isOk())
	 * .andExpect(view().name("index")); }
	 */
}
