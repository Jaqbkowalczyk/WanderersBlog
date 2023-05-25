package com.fdmgroup.WanderersBlog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Role;
import com.fdmgroup.WanderersBlog.repository.RoleRepository;




@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private RoleRepository repo;
	
	@Override
	public Role findByRoleName(String roleName) {
		Optional<Role> optRole = repo.findByRoleName(roleName);
		
		return optRole.orElse(new Role("default role"));
	}

}
