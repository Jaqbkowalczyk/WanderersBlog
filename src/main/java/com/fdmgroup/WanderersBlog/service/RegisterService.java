package com.fdmgroup.WanderersBlog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;



@Service
public class RegisterService implements IRegisterService {

	@Autowired
	private DefaultUserDetailsService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder encoder;

	public String registerSubmit(User user, String confirmPassword, ModelMap model) {
		Optional<User> userFromDatabase = userService.findByUsername(user.getUsername());
		if (userFromDatabase.isPresent()) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}
		
		 if (!user.getPassword().equals(confirmPassword)) {
		        model.addAttribute("message", "Passwords do not match");
		        return "register";
		    }
		

		user.setRole(roleService.findByRoleName("Customer"));
		user.setPassword(encoder.encode(user.getPassword()));

		userService.saveUser(user);
		
		return "index";
	}
}
