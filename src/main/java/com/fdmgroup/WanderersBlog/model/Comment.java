package com.fdmgroup.WanderersBlog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	private Story story;

	@ManyToOne
	private User user;

	private String content;

	@ManyToOne
	private Comment parentComment;

	@OneToMany(mappedBy = "parentComment")
	private List<Comment> replies;
	
	private Date date;

	

	public Comment() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	
	public void addReply(Comment comment) {
		if(this.replies== null) {
			this.replies = new ArrayList<Comment>();
		}
		this.replies.add(comment);
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, parentComment, replies, story, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(content, other.content) && Objects.equals(parentComment, other.parentComment)
				&& Objects.equals(replies, other.replies) && Objects.equals(story, other.story)
				&& Objects.equals(user, other.user);
	}

	
	
}
