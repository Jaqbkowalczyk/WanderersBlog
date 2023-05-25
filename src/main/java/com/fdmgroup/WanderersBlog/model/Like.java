package com.fdmgroup.WanderersBlog.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {
	
	
	@Id
    @GeneratedValue
    private int likeId;
	
	private int userId;
	private int storyId;
	
	public Like() {
		super();
	}

	public Like(int userId, int storyId) {
		super();
		this.userId = userId;
		this.storyId = storyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	@Override
	public String toString() {
		return "Like [userId=" + userId + ", storyId=" + storyId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(storyId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		return storyId == other.storyId && userId == other.userId;
	}
	
	
}
