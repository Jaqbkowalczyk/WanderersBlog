package com.fdmgroup.WanderersBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.Rating;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.IOfferService;
import com.fdmgroup.WanderersBlog.service.IStoryService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.RatingService;
import com.fdmgroup.WanderersBlog.service.UserService;

@Controller
public class RatingController {
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private LoginService logService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/rate/{username}")
	public String rate(ModelMap model, @RequestParam(required=false) Integer value, @RequestParam String comment, @PathVariable String username) {
		logService.isLoggedIn(model);
		User user = logService.getLoggedUser();
		User viewUser = userService.findByUsername(username);
		Rating rating;
		if (value == null) {
			rating = new Rating();
			rating.setComment(comment);
			rating.setRater(user);
			rating.setUser(viewUser);
		}else {
		rating = new Rating();
		rating.setComment(comment);
		rating.setRater(user);
		rating.setUser(viewUser);
		rating.setValue((int) value);
		}
		
		ratingService.save(rating);
		
		return "redirect:/userProfile/" + username;		
	}
	
	
	
	
	
}
