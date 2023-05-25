package com.fdmgroup.WanderersBlog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
public class User {
	@Id
	@GeneratedValue
	private Integer userId;
	String username, password, email, firstName, surName;
	
	@ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roleList;
	
	@ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Conversation.class, fetch = FetchType.EAGER)
	private List<Conversation> conversations;
	
	@OneToMany
	private List<Subscription> subscriptions;
	
	public User() {	}
	
	
	public User(String username, String password, String email, String firstName, String surName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.surName = surName;
	}



	public User(String username) {
		this.username = username;
	}


	public Integer getId() {
		return userId;
	}

	public void setId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public void setRole(Role role) {
		if(this.roleList == null) {
			this.roleList = new ArrayList<>();
		}
		this.roleList.add(role);
	}



	public List<Conversation> getConversations() {
		return conversations;
	}



	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public void addConversation(Conversation conversation) {
		if(this.conversations == null) {
			this.conversations = new ArrayList<>();
		}
		this.conversations.add(conversation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, password, roleList, surName, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(password, other.password) && Objects.equals(roleList, other.roleList)
				&& Objects.equals(surName, other.surName) && Objects.equals(username, other.username);
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + "]";
	}
	
	
	
}
