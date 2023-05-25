package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Role;
import com.fdmgroup.WanderersBlog.repository.RoleRepository;
import com.fdmgroup.WanderersBlog.service.RoleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {
	
	@InjectMocks
	RoleService roleService;
	
	@MockBean
	RoleRepository mockRoleRepository;
	
	@Test
	public void test_findByRoleName_roleExists() {
		String name = "testRole";
		Role expectedRole = new Role();
		expectedRole.setName(name);
		when(mockRoleRepository.findByRoleName(name)).thenReturn(Optional.of(expectedRole));
		Role returnedRole = roleService.findByRoleName(name);
		
		verify(mockRoleRepository, times(1)).findByRoleName(name);
		assertEquals(expectedRole, returnedRole);
	}
	
	@Test
	public void test_findByRoleName_roleDoesNotExists() {
		String name = "non-existent role";
		when(mockRoleRepository.findByRoleName(name)).thenReturn(Optional.empty());
		Role returnedRole = roleService.findByRoleName(name);
		
		verify(mockRoleRepository, times(1)).findByRoleName(name);
		assertEquals("default role", returnedRole.getName());
	}
	
	
	
}
