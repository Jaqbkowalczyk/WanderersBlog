package com.fdmgroup.WanderersBlog.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer value = 0;
	private String comment;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private User rater;

	public Rating() {
		super();
	}

	public Rating(Integer value, String comment, User user, User rater) {
		super();
		this.value = value;
		this.comment = comment;
		this.user = user;
		this.rater = rater;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", value=" + value + ", comment=" + comment + ", user=" + user + ", rater=" + rater
				+ "]";
	}
	
	


	
}
