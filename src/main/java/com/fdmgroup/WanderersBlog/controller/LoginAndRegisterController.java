package com.fdmgroup.WanderersBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.RegisterService;

@Controller
public class LoginAndRegisterController {
	@Autowired
	private LoginService logService;

	@Autowired
	private RegisterService registerService;

	@GetMapping("/login")
	public String login(ModelMap model) {
		logService.isLoggedIn(model);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		logService.isLoggedIn(model);
		return "register";
	}

	@GetMapping("/logged")
	public String loggedUser(ModelMap model) {
		logService.isLoggedIn(model);
		return "redirect:/";
	}	
	
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user") User user, @ModelAttribute("address") 
			@RequestParam("confirmPassword") String confirmPassword, ModelMap model) {
		
		return registerService.registerSubmit(user, confirmPassword, model);
	}
}