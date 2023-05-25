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
import javax.persistence.OneToOne;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	private Integer id;

	@OneToOne
	private User user;

	@ElementCollection
	private List<String> images;

	private String title, location;
	
	@Column(length = 5000)
	private String description;

	private Date date, startDate, endDate;

	private Integer howManyLookingFor;

	@ElementCollection
	private List<Integer> requestsFromId;
	
	public Offer() {
	}

	public Offer(User user, String title, String description, String location, Date date, Date startDate, Date endDate,
			Integer howManyLookingFor) {
		super();
		this.user = user;
		this.title = title;
		this.description = description;
		this.location = location;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.howManyLookingFor = howManyLookingFor;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getHowManyLookingFor() {
		return howManyLookingFor;
	}

	public void setHowManyLookingFor(Integer howManyLookingFor) {
		this.howManyLookingFor = howManyLookingFor;
	}

	
	public List<Integer> getRequestsFromId() {
		return requestsFromId;
	}

	public void setRequestsFromId(List<Integer> requestsFromId) {
		this.requestsFromId = requestsFromId;
	}

	public void addRequest(Integer requestId) {
		if (this.requestsFromId == null) {
			this.requestsFromId = new ArrayList<Integer>();
		}
		this.requestsFromId.add(requestId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, description, images, location, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(images, other.images) && Objects.equals(location, other.location)
				&& Objects.equals(title, other.title) && Objects.equals(user, other.user);
	}
}
