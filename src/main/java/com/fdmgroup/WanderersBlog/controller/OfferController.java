package com.fdmgroup.WanderersBlog.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.IConversationService;
import com.fdmgroup.WanderersBlog.service.IMessageService;
import com.fdmgroup.WanderersBlog.service.IOfferService;
import com.fdmgroup.WanderersBlog.service.IUserService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.util.FileUploadUtil;
import com.fdmgroup.WanderersBlog.util.Filtering;

@Controller
public class OfferController {
	@Autowired
	private LoginService logService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IOfferService service;
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private IConversationService conversationService;
	

	User user;

	@GetMapping(value = "/create-offer")
	public String goCreateOffer(ModelMap model) {
		logService.isLoggedIn(model);
		return "create-offer";
	}

	@PostMapping(value = "/create-offer")
	public String createOffer(ModelMap model, @RequestParam String title,
			@RequestParam(required = false) String description, @RequestParam Integer howManyLookingFor,
			@RequestParam String startDate, @RequestParam String endDate, @RequestParam String location,
			@RequestParam(value = "images", required = false) MultipartFile[] images)
			throws IOException, ParseException {
		Offer offer = new Offer();
		offer.setTitle(title);
		offer.setDescription(description);
		offer.setLocation(location);
		offer.setHowManyLookingFor(howManyLookingFor);
		offer.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
		offer.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
		offer.setDate(new Date());
		user = logService.getLoggedUser();
		offer.setUser(user);
		service.saveOffer(offer);
		for (MultipartFile image : images) {
			if (image != null && !image.isEmpty()) {
				String fileName = StringUtils.cleanPath(image.getOriginalFilename());
				String uploadDir = "./src/main/webapp/img/offer/" + offer.getId();
				FileUploadUtil.saveFile(uploadDir, fileName, image);
				offer.addImage("/img/offer/" + offer.getId() + "/" + fileName);
			}
		}
		service.saveOffer(offer);
		model.addAttribute("offer", offer);
		return "redirect:/offer/" + offer.getId();
	}
	
	@PostMapping(value="/response-to-offer/{offerId}")
	public String responseToOffer(ModelMap model,@PathVariable int offerId) {
		Message message = new Message();
		User wanderer = userService.findByUsername("Wanderer");
		Offer offer = service.findById(offerId);
		String loggedUsername = logService.getLoggedUser().getUsername();
		boolean alreadyRequested = false;
		for (Integer requestId : offer.getRequestsFromId()) {
			if(logService.getLoggedUser().getId() == requestId) {
				alreadyRequested = true;
			}
		}
		
		if(!alreadyRequested){
			
			messageService.saveMessage(message);
			message.setContent(""
					+ "<br>"
					+ "You have new response to Offer!"
					+ "<br>"
					+ "<br>"
					+ "For offer: " + offer.getTitle() + "<br>"
					+ "From user: " + loggedUsername + "<br>"
					+ "See their profile: <a href=\"/userProfile/"+ loggedUsername +"\">"+ loggedUsername +"</a>" + "<br>"
					+ "Places left: " + offer.getHowManyLookingFor() + "<br>"
					+ "<br>"
					+ "<br>"
					+ "Do you agree to travel together?"+ "<br>"
					+ "<form action=\"/response-to-user/"+ logService.getLoggedUser().getId() +"/"+offer.getId() +"/"+message.getId() + "/accept\" method=\"POST\">"
					+ "			<input type=\"submit\" value=\"Yes, welcome on board!\">"
					+ "</form>" 
					+ "<form action=\"/response-to-user/"+ logService.getLoggedUser().getId() +"/"+offer.getId() +"/"+message.getId() +"/decline\" method=\"POST\">"
					+ "			<input type=\"submit\" value=\"No, maybe next time!\">"
					+ "	</form>"
					+ "<br>"
					+ "<br>"
					);
			messageService.sendMessage(message, offer.getTitle(), wanderer.getId(), offer.getUser().getId());
			offer.addRequest(logService.getLoggedUser().getId());
			service.saveOffer(offer);
			model.addAttribute("message", "Your response has been sent!");
		}else {
			model.addAttribute("message", "You have already responded to this offer!");
		}
		return goToOffer(model, offerId);
	}
	
	@PostMapping(value="/response-to-user/{userId}/{offerId}/{messageId}/accept")
	public String responseToUserAccept(ModelMap model,@PathVariable int userId,@PathVariable int offerId,@PathVariable Long messageId ) {
		Message message = new Message();
		User wanderer = userService.findByUsername("Wanderer");
		Offer offer = service.findById(offerId);
		String offerUsername = offer.getUser().getUsername();
		User requestingUser = userService.findByUserId(userId);
		offer.setHowManyLookingFor(offer.getHowManyLookingFor()-1);
		if(offer.getHowManyLookingFor()<=0) {
			service.deleteOffer(offer);
		}
		Conversation conversation = conversationService.findBySubjectAndSenderAndRecipient(offer.getTitle(), requestingUser, offer.getUser());
		
		if(conversation==null) {
			conversation = new Conversation(offer.getTitle(), userService.findByUserId(userId), offer.getUser());
			conversationService.startConversation(conversation);
		}
		message.setContent(""
				+ "<br>"
				+ "Your request to join has been accepted!"
				+ "<br>"
				+ "<br>"
				+ "For offer: " + offer.getTitle() + "<br>"
				+ "By user: " + offerUsername + "<br>"
				+ "See their profile: <a href=\"/userProfile/"+ offerUsername +"\">"+ offerUsername +"</a>" + "<br>"
				+ "<br>"
				+ "<br>"
				+ "You can now discuss the details:" + "<br>"
				+ "<a href=\"/conversation/"+ conversation.getId() +"\">SEND A MESSAGE</a>" 
				+ "<br>"
				+ "<br>"
				);
		messageService.sendMessage(message, offer.getTitle(), wanderer.getId(), requestingUser.getId());
		
		Message message2 = messageService.getMessageById(messageId);
		message2.setContent(""
				+ "<br>"
				+ "Your have accepted traveller "+ requestingUser.getUsername() +" to join you!"
				+ "<br>"
				+ "<br>"
				+ "See their profile: <a href=\"/userProfile/"+ requestingUser.getUsername() +"\">"+ requestingUser.getUsername() +"</a>" + "<br>"
				+ "<br>"
				+ "<br>"
				+ "You can now discuss the details:" + "<br>"
				+ "<a href=\"/conversation/"+ conversation.getId() +"\">SEND A MESSAGE</a>" 
				+ "<br>"
				+ "<br>"
				);
		messageService.saveMessage(message2);
		Conversation conversationWithWanderer = conversationService.findBySubjectAndSenderAndRecipient(offer.getTitle(), wanderer, offer.getUser());
		return "redirect:/conversation/" + conversationWithWanderer.getId();
	}
	
	@PostMapping(value="/response-to-user/{userId}/{offerId}/{messageId}/decline")
	public String responseToUserDecline(ModelMap model,@PathVariable int userId,@PathVariable int offerId, @PathVariable Long messageId ) {
		Message message = new Message();
		User wanderer = userService.findByUsername("Wanderer");
		Offer offer = service.findById(offerId);
		String offerUsername = offer.getUser().getUsername();
		User requestingUser = userService.findByUserId(userId);
		
		
		message.setContent(""
				+ "<br>"
				+ "Your request to join has been declined!"
				+ "<br>"
				+ "<br>"
				+ "For offer: " + offer.getTitle() + "<br>"
				+ "By user: " + offerUsername + "<br>"
				+ "<br>"
				+ "<a href=\"/FindCompany\">Find other offers!</a>"
				+ "<br>"
				+ "<br>"
				);
		messageService.sendMessage(message, offer.getTitle(), wanderer.getId(), requestingUser.getId());
		
		Message message2 = messageService.getMessageById(messageId);
		message2.setContent(""
				+ "<br>"
				+ "<br>"
				+ "Your have declined traveller "+ requestingUser.getUsername() +" to join you!"
				+ "<br>"
				+ "<br>"
				);
		messageService.saveMessage(message2);
		
		
		Conversation conversationWithWanderer = conversationService.findBySubjectAndSenderAndRecipient(offer.getTitle(), wanderer, offer.getUser());
		return "redirect:/conversation/" + conversationWithWanderer.getId();
	}

	@GetMapping(value = "/offer/{id}")
	public String goToOffer(ModelMap model, @PathVariable int id) // throws PlaceNotFoundException
	{
		logService.isLoggedIn(model);
		Offer offer = service.findById(id);
		model.addAttribute("offer", offer);
		if (!offer.getImages().isEmpty()) {
			model.addAttribute("mainPhotoUrl", offer.getImages().get(0));
		} else {
			model.addAttribute("mainPhotoUrl", "");
		}
		model.addAttribute("pictureUrls", offer.getImages());
//		model.addAttribute("productRating", ratingService.getAverageProductRating(product));
		user = offer.getUser();
		model.addAttribute("user", user);
		return "offer";
	}

//	@GetMapping("/filteredOffer")
//	public String goToFiltered(ModelMap model) {
//		logService.isLoggedIn(model);
//		return "filtered";
//	}

	@PostMapping("/filteredOffer")
	public String filterStories(ModelMap model, @RequestParam String filter) {
		logService.isLoggedIn(model);

		List<Offer> finalFilteredOffers = service.filterOffers(filter);
		model.addAttribute("resultsOfSearch", finalFilteredOffers);
		model.addAttribute("offers", service.findAllOffers());
		return "find-company";
	}

//	@GetMapping("/dropDownFiltersOffer")
//	public String goToDropDownFilters(ModelMap model) {
//		logService.isLoggedIn(model);
//		return "dropDownFiltersOffer";
//	}

	@PostMapping("/dropDownFiltersOffer")
	public String filteringFunction(ModelMap model, @RequestParam String filter,
			@RequestParam(required = false) String location,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, 
			@RequestParam(required = false) int howManyLookingFor) {
		logService.isLoggedIn(model);
		Filtering filtering = new Filtering(location);

		List<Offer> searchedByDestination = new ArrayList<>();
		List<Offer> searchedOffers = new ArrayList<>();
		List<Offer> searchedByDateRange = new ArrayList<>();
		List<Offer> searchedByByStartDate = new ArrayList<>();
		List<Offer> searchedByByEndDate = new ArrayList<>();
		List<Offer> searchedByByHowManyLookingFor = new ArrayList<>();

		if (location != "") {
			searchedByDestination = service.findByLocation(location);
			if (searchedOffers.isEmpty()) {
				searchedOffers.addAll(searchedByDestination);
			} else {
				searchedOffers.retainAll(searchedByDestination);
			}
		}

		if (startDate != null && endDate != null) {
			System.out.println(startDate);
			searchedByDateRange = service.findByDateRange( startDate, endDate);
			if (searchedOffers.isEmpty()) {
				searchedOffers = searchedByDateRange;
			} else {
				searchedOffers.retainAll(searchedByDateRange);
			}
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} else if (startDate != null) {
			searchedByByStartDate = service.findByStartDate(startDate);
			if (searchedOffers.isEmpty()) {
				searchedOffers = searchedByByStartDate;
			} else {
				searchedOffers.retainAll(searchedByByStartDate);
			}
			model.addAttribute("startDate", startDate);
			
		} else if (endDate != null) {
			searchedByByEndDate = service.findByEndDate(endDate);
			if (searchedOffers.isEmpty()) {
				searchedOffers = searchedByByEndDate;
			} else {
				searchedOffers.retainAll(searchedByByEndDate);
			}model.addAttribute("endDate", endDate);
		}
		
		if(howManyLookingFor != 0) {
			System.out.println("How many looking for: " + howManyLookingFor);
			searchedByByHowManyLookingFor = service.findByHowManyLookingFor(howManyLookingFor);
			if (searchedOffers.isEmpty()) {
				searchedOffers.addAll(searchedByByHowManyLookingFor);
		}
			
		}

		model.addAttribute("resultsOfSearch", searchedOffers);
		model.addAttribute("filter", filter);
		model.addAttribute("filtering", filtering);
		
		return "find-company";
	}

}
