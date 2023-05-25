package com.fdmgroup.WanderersBlog.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Subscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private User creator;
	
	private boolean notificationsOn = false;
	
	public Subscription() {}
	
	public Subscription(User user, User creator) {
		super();
		this.user = user;
		this.creator = creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public boolean getNotificationsOn() {
		return notificationsOn;
	}

	public void setNotificationsOn(boolean notificationsOn) {
		this.notificationsOn = notificationsOn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, notificationsOn, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		return Objects.equals(creator, other.creator) && notificationsOn == other.notificationsOn
				&& Objects.equals(user, other.user);
	}

	
	
	
}
