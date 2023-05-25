package com.fdmgroup.WanderersBlog.service;

import com.fdmgroup.WanderersBlog.model.User;

public interface IUserService {

	User findByUsername(String username);

	User findByUserId(int id);

}
