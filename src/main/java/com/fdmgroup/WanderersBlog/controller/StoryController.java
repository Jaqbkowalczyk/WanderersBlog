package com.fdmgroup.WanderersBlog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.WanderersBlog.model.Comment;
import com.fdmgroup.WanderersBlog.model.Like;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.Subscription;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.service.ICommentService;
import com.fdmgroup.WanderersBlog.service.ILikeService;
import com.fdmgroup.WanderersBlog.service.IStoryService;
import com.fdmgroup.WanderersBlog.service.ISubscriptionService;
import com.fdmgroup.WanderersBlog.service.IUserService;
import com.fdmgroup.WanderersBlog.service.LoginService;
import com.fdmgroup.WanderersBlog.util.FileUploadUtil;
import com.fdmgroup.WanderersBlog.util.Filtering;

@Controller
public class StoryController {

	@Autowired
	private LoginService logService;

	@Autowired
	private ICommentService commentService;

	@Autowired
	private IStoryService service;

	@Autowired
	private ILikeService likeService;

	@Autowired
	private ISubscriptionService subscriptionService;

	User user;

	@GetMapping(value = "/create-story")
	public String goCreateStory(ModelMap model) {
		logService.isLoggedIn(model);
		return "create-story";
	}

	@PostMapping(value = "/create-story")
	public String createStory(ModelMap model, @RequestParam String title,
			@RequestParam(required = false) String description, @RequestParam String category,
			@RequestParam String location, @RequestParam(value = "images", required = false) MultipartFile[] images)
			throws IOException {
		Story story = new Story();
		story.setTitle(title);
		story.setDescription(description);
		story.setCategory(category);
		story.setLocation(location);
		story.setDate(new Date());
		user = logService.getLoggedUser();
		story.setUser(user);
		service.saveStory(story);
		for (MultipartFile image : images) {
			if (image != null && !image.isEmpty()) {
				String fileName = StringUtils.cleanPath(image.getOriginalFilename());
				String uploadDir = "./src/main/webapp/img/" + story.getId();
				FileUploadUtil.saveFile(uploadDir, fileName, image);
				story.addImage("/img/" + story.getId() + "/" + fileName);
			}
		}
		service.saveStory(story);
		subscriptionService.notifySubscribers(user);
		model.addAttribute("story", story);
		return "redirect:/story/" + story.getId();
	}

	@GetMapping(value = "/story/{id}")
	public String goToStory(ModelMap model, @PathVariable int id) // throws PlaceNotFoundException
	{

		Story story = service.findById(id).get();
		model.addAttribute("story", story);
		if (!story.getImages().isEmpty()) {
			model.addAttribute("mainPhotoUrl", story.getImages().get(0));
		} else {
			model.addAttribute("mainPhotoUrl", "");
		}
		model.addAttribute("pictureUrls", story.getImages());
		user = story.getUser();
		model.addAttribute("user", user);
		if (logService.isLoggedIn(model)) {
			subscriptionService.checkSubscription(model, logService.getLoggedUser(), user);
		}
		model.addAttribute("otherStoriesFromCategory", service.recommendStories(story));
		populateComments(model, story);
		return "story";
	}

	@PostMapping(value = "/comment/{storyId}")
	public String postComment(ModelMap model, @PathVariable int storyId, @RequestParam String content) {
		Comment comment = new Comment();
		Story story = service.findById(storyId).get();
		comment.setContent(content);
		comment.setStory(story);
		comment.setDate(new Date());
		comment.setUser(logService.getLoggedUser());
		commentService.saveComment(comment);
		story.addComment(comment);
		service.saveStory(story);
		return goToStory(model, storyId);
	}

	@PostMapping(value = "/reply/{commentId}")
	public String replyToComment(ModelMap model, @PathVariable int commentId, @RequestParam String content) {
		Comment comment = new Comment();
		Comment parentComment = commentService.findById(commentId).get();
		Story story = parentComment.getStory();
		comment.setContent(content);
		comment.setStory(story);
		comment.setDate(new Date());
		comment.setUser(logService.getLoggedUser());
		comment.setParentComment(parentComment);
		parentComment.addReply(comment);
		commentService.saveComment(comment);
		commentService.saveComment(parentComment);
		story.addComment(comment);
		service.saveStory(story);
		return goToStory(model, story.getId());
	}

	@GetMapping(value = "/reply/{commentId}")
	public String replyBox(ModelMap model, @PathVariable int commentId) {
		Comment parentComment = commentService.findById(commentId).get();
		Story story = parentComment.getStory();
		model.addAttribute("replyBox", true);
		model.addAttribute("commentId", commentId);
		return goToStory(model, story.getId());
	}

	@GetMapping("/filtered")
	public String goToFiltered(ModelMap model) {
		logService.isLoggedIn(model);
		return "filtered";
	}

	@PostMapping("/filtered")
	public String filterStories(ModelMap model, @RequestParam String filter) {
		logService.isLoggedIn(model);

		List<Story> finalFilteredStories = service.filterStories(filter);
		System.out.println(finalFilteredStories);
		model.addAttribute("filteredStories", finalFilteredStories);
		model.addAttribute("stories", service.findAllStories());
		return "filtered";
	}

	@PostMapping("/dropDownFilters")
	public String filteringFunction(ModelMap model, @RequestParam String filter,
			@RequestParam(required = false) String location, @RequestParam(required = false) String category,
			@RequestParam(required = false) List<Story> filteredStories) {
		logService.isLoggedIn(model);
		Filtering filtering = new Filtering(category, location);

		List<Story> searchedByCategory = new ArrayList<>();
		List<Story> searchedByLocation = new ArrayList<>();
		List<Story> searchedStories = new ArrayList<>();

		if (filteredStories != null) {
			searchedStories.addAll(filteredStories);
		}

		if (category != "") {
			searchedByCategory = service.findByCategory(category);
			if (searchedStories.isEmpty()) {
				searchedStories.addAll(searchedByCategory);
			}
			searchedStories.retainAll(searchedByCategory);
		}

		if (location != "") {
			searchedByLocation = service.findByLocation(location);
			if (searchedStories.isEmpty()) {
				searchedStories.addAll(searchedByLocation);
			}
			searchedStories.retainAll(searchedByLocation);
		}

		searchedStories.stream().distinct().collect(Collectors.toList());

		model.addAttribute("filteredStories", searchedStories);
		model.addAttribute("filter", filter);
		model.addAttribute("filtering", filtering);
		return "filtered";
	}

	@GetMapping("/Category/{category}")
	public String goToCategory(ModelMap model, @PathVariable String category) {
		logService.isLoggedIn(model);
		category = category.replace("_", " ");

		List<Story> listOfStories = service.findByCategoryIgnoreCase(category);
		model.addAttribute("stories", listOfStories);
		model.addAttribute("category", category.toUpperCase());
		return "show-posts-of-category";

	}

	@GetMapping("/Location/{location}")
	public String goToLocation(ModelMap model, @PathVariable String location) {
		logService.isLoggedIn(model);
		location = location.replace("_", " ");

		List<Story> listOfStories = service.findByLocationIgnoreCase(location);
		model.addAttribute("filteredStories", listOfStories);
		return "filtered";

	}

	@PostMapping("like/{id}")
	public String likePost(ModelMap model, @PathVariable int id) {
		logService.isLoggedIn(model);
		user = logService.getLoggedUser();
		Story story = service.findById(id).get();

		Like like = likeService.findByUserIdAndStoryId(user.getId(), id);
		if (like != null) {
			likeService.delete(like);
			story.setLikes(story.getLikes() - 1);
		} else {
			like = new Like();
			like.setUserId(user.getId());
			like.setStoryId(id);
			likeService.save(like);
			story.setLikes(story.getLikes() + 1);
		}

		service.saveStory(story);

		return goToStory(model, story.getId());
	}

	@PostMapping("subscribe/{storyid}")
	public String subscribe(ModelMap model, @PathVariable int storyid) {
		user = logService.getLoggedUser();
		Story story = service.findById(storyid).get();
		User creator = story.getUser();
		Subscription subscription;
		if (subscriptionService.findByUserAndCreator(user, creator) == null) {
			subscription = new Subscription(user, creator);
			subscriptionService.subscribe(subscription);
		} else {
			subscription = subscriptionService.findByUserAndCreator(user, creator);
		}

		model.addAttribute("subscription", subscription);
		return goToStory(model, story.getId());
	}

	@PostMapping("unsubscribe/{storyid}")
	public String unsubscribe(ModelMap model, @PathVariable int storyid) {
		user = logService.getLoggedUser();
		Story story = service.findById(storyid).get();
		User creator = story.getUser();
		Subscription subscription = subscriptionService.findByUserAndCreator(user, creator);
		subscriptionService.unsubscribe(subscription);
		return goToStory(model, story.getId());
	}

	@PostMapping("notify/{storyid}/{subscriptionId}")
	public String notify(ModelMap model, @PathVariable int storyid, @PathVariable Long subscriptionId) {
		Story story = service.findById(storyid).get();
		Subscription subscription = subscriptionService.findById(subscriptionId);
		if (!subscription.getNotificationsOn()) {
			subscription.setNotificationsOn(true);
		} else {
			subscription.setNotificationsOn(false);
		}
		subscriptionService.subscribe(subscription);
		return goToStory(model, story.getId());
	}

	private void populateComments(ModelMap model, Story story) {
		List<Comment> comments = story.getComments();
		List<Comment> replyComments = new ArrayList<>();
		if (comments != null) {
			for (Comment comment : comments) {
				if (comment.getParentComment() != null) {
					replyComments.add(comment);
				}
			}
			for (Comment reply : replyComments) {
				comments.remove(reply);
			}
			model.addAttribute("comments", comments);
			model.addAttribute("replies", replyComments);
		}
	}

}
