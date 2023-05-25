package com.fdmgroup.WanderersBlog.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.WanderersBlog.model.User;



public class UserPrincipal implements UserDetails {

	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}

	//below method needs implementation for our project
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoleList();
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	
	
	public String getFirstName() {
		// TODO Auto-generated method stub
		return user.getFirstName();
	}
	
	public int getId() {
		return user.getId();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
