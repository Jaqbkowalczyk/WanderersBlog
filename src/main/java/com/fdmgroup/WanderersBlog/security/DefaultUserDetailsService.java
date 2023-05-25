package com.fdmgroup.WanderersBlog.security;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.UserRepository;



@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private UserRepository repo;
	
	@Autowired
	public DefaultUserDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = findByUsername(username);
		User user = optUser.orElseThrow(() -> new UsernameNotFoundException(username));
		return new UserPrincipal(user);
	}
	
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}
	
	public User saveUser(User user) {
		return repo.save(user);
	}

}