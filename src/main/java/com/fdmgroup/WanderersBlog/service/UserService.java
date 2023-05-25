package com.fdmgroup.WanderersBlog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.UserRepository;


@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository repo;
	
	@Override
	public User findByUsername(String username) {
		Optional<User> optUser = repo.findByUsername(username);
		return optUser.orElse(new User("default user"));
	}

	@Override
	public User findByUserId(int id) {
		Optional<User> optUser = repo.findByUserId(id);
		return optUser.orElse(new User("default user"));
	}

}
