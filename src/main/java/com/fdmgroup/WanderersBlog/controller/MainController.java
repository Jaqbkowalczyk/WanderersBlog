package com.fdmgroup.WanderersBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.WanderersBlog.service.IOfferService;
import com.fdmgroup.WanderersBlog.service.IStoryService;
import com.fdmgroup.WanderersBlog.service.LoginService;

@Controller
public class MainController {
	@Autowired
	private LoginService login;
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private IOfferService offerService;

	@GetMapping(value = "/")
	public String goToIndex(ModelMap model) {
		login.isLoggedIn(model);
		model.addAttribute("recentStories", storyService.getAllStoriesSortedByDate().subList(0, 3));
		model.addAttribute("mostLikedStories", storyService.findAllStoriesOrderByLikes().subList(0, 4));
		model.addAttribute("recentOffers", offerService.getAllOffersSortedByDate().subList(0, 3));
		return "index";
	}

	@GetMapping("/Category")
	public String goToCategory(ModelMap model) {
		login.isLoggedIn(model);
		return "show-posts-of-category";
	}

	@GetMapping("/FindCompany")
	public String goToFindCompany(ModelMap model) {
		login.isLoggedIn(model);
		return "find-company";
	}

}
