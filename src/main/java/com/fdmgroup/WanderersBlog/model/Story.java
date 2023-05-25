package com.fdmgroup.WanderersBlog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Story {
	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne
	private User user;

	@OneToMany
	private List<Comment> comments;

	private String title, category, location;
	
	@Column(length=5000)
	private String description;
	
	@ElementCollection
	private List<String> images;

	private Date date;

	private Double rating;

	private Integer likes = 0;

	public Story() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void addImage(String image) {
		if (this.images == null) {
			this.images = new ArrayList<String>();
		}
		this.images.add(image);
	}

	public void addComment(Comment comment) {
		if (this.comments == null) {
			this.comments = new ArrayList<Comment>();
		}
		this.comments.add(comment);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getLikes() {
		
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, comments, date, description, images, likes, location, rating, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		return Objects.equals(category, other.category) && Objects.equals(comments, other.comments)
				&& Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(images, other.images) && Objects.equals(likes, other.likes)
				&& Objects.equals(location, other.location) && Objects.equals(rating, other.rating)
				&& Objects.equals(title, other.title) && Objects.equals(user, other.user);
	}

}
