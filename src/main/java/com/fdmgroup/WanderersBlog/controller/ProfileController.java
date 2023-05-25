package com.fdmgroup.WanderersBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.WanderersBlog.security.DefaultUserDetailsService;
import com.fdmgroup.WanderersBlog.security.UserPrincipal;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.IOfferService;
import com.fdmgroup.WanderersBlog.service.IStoryService;
import com.fdmgroup.WanderersBlog.service.IUserService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.service.MessageService;
import com.fdmgroup.WanderersBlog.service.RatingService;

@Controller
public class ProfileController {
	@Autowired
	private LoginService logService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStoryService storyService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private IOfferService offerService;
	
	@Autowired
	private DefaultUserDetailsService defaultUserDetailsService;
	
	User user;
	
	@GetMapping("/userProfile/{username}")
	public String goToShowUserProfile(ModelMap model, @PathVariable String username) {
		logService.isLoggedIn(model);
		user = logService.getLoggedUser();
		User viewUser = userService.findByUsername(username);
		List<Story> userStories = storyService.findByUser(viewUser);
		List<Offer> userOffers = offerService.findByUser(viewUser);
		model.addAttribute("viewUser", viewUser);
		model.addAttribute("user", user);
		model.addAttribute("ratings", ratingService.findRatingByUser(viewUser));
		model.addAttribute("userRating", ratingService.getAverageUserRating(viewUser));
		model.addAttribute("offers",userOffers);
		model.addAttribute("stories", userStories);
		
		return "user-profile";
	}
	
	
	@GetMapping("/editUserDetails")
	public ModelAndView editUserDetails(ModelMap model) {
		logService.isLoggedIn(model);
		ModelAndView modelAndView = new ModelAndView("edit-user-details");
		User loggedUser = logService.getLoggedUser();
		modelAndView.addObject("user", loggedUser);

		return modelAndView;
	}
	
	@PostMapping("/editUserDetails")
	public String editUserDetails(@ModelAttribute("user") User updatedUser, ModelMap model) {
		System.out.println("current user updated " + updatedUser);
		logService.isLoggedIn(model);
	 
	    // Get the current logged-in user from the security
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User loggedInUser = ((UserPrincipal)authentication.getPrincipal()).getUser();
	 
	    // Update the current user's details with the updated information
	    loggedInUser.setFirstName(updatedUser.getFirstName());
	    loggedInUser.setSurName(updatedUser.getSurName());
	    loggedInUser.setEmail(updatedUser.getEmail());
	    
	    model.addAttribute("userName", loggedInUser.getUsername());
		model.addAttribute("userFristName", loggedInUser.getFirstName());
		model.addAttribute("userSurName", loggedInUser.getSurName());
		model.addAttribute("userEmail", loggedInUser.getEmail());
	 
	    // Save the updated user to the database
	    
	    defaultUserDetailsService.saveUser(loggedInUser);
	 
	    return goToShowUserProfile(model,loggedInUser.getUsername());
	}
	
	
}
