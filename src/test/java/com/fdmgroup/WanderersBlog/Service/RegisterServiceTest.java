package com.fdmgroup.WanderersBlog.Service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;
import com.fdmgroup.WanderersBlog.service.RegisterService;
import com.fdmgroup.WanderersBlog.service.RoleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RegisterServiceTest {
	@InjectMocks
	RegisterService registerService;
	
	@MockBean
	RoleService roleService;
	
	@MockBean
	PasswordEncoder mockPasswordEnconder;
	
	@MockBean
	DefaultUserDetailsService mockDefaultUserDetailsService;
}
