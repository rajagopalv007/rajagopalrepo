package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.ApplicationRole;
import com.example.demo.domain.ApplicationUser;
import com.example.demo.domain.repository.ApplicationRoleRepository;
import com.example.demo.domain.repository.ApplicationUserRepository;

@Component
public class RoleService {

	private static Logger logger = LogManager.getLogger();
	
	
	@Autowired
	private ApplicationRoleRepository roleRepository;
	
	
	public List<ApplicationRole> getAllRoles() {
		List<ApplicationRole> appRoles=new ArrayList<>();
		roleRepository.findAll().forEach((role)->appRoles.add(role));
		return appRoles;
	}
	
}
