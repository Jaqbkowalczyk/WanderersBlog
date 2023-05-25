package com.fdmgroup.WanderersBlog.service;

import com.fdmgroup.WanderersBlog.model.Role;

public interface IRoleService {

	Role findByRoleName(String roleName);

}
