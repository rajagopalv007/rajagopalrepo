package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.domain.ApplicationUser;
import com.example.demo.domain.repository.ApplicationRoleRepository;
import com.example.demo.domain.repository.ApplicationUserRepository;

@Component
public class UserService {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	private ApplicationUserRepository userRepository;
	
	@Autowired
	private ApplicationRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public void createSimpleUser(ApplicationUser appUser) {
		
		appUser.setEncryptedPassword(encoder.encode(appUser.getPlainPassword()));
		appUser.setUserRoles(new HashSet<>());
		//appUser.setEnabled(true);
		userRepository.save(appUser);
	}
	
	@Transactional
	public void createUser(ApplicationUser appUser) {
		
		appUser.setEncryptedPassword(encoder.encode(appUser.getPlainPassword()));
		//appUser.setUserRoles(new HashSet<>());
		//appUser.setEnabled(true);
		userRepository.save(appUser);
	}
	
	@Transactional
	public void addRoleToUser(UUID userId,UUID roleId) {
		/*Optional<ApplicationUser> appUserOpt=userRepository.findById(UUID.fromString(userId));
		Optional<ApplicationRole> appRoleOpt=roleRepository.findById(UUID.fromString(roleId));
		//()->throw new RuntimeException("User with id "+userId+" not found")
		ApplicationUser appUser=appUserOpt.orElseThrow(()->new RuntimeException("User with id "+userId+" not found"));
		ApplicationRole appRole=appRoleOpt.orElseThrow(()->new RuntimeException("Role with id "+roleId+" not found"));
		
		appUser.addRoleToUser(appRole);
		
		userRepository.save(appUser);*/
		userRepository.addRoleToUser(userId, roleId);
	}
	
	@Transactional
	public List<ApplicationUser> getAllUsers() {
		List<ApplicationUser> appUsers=new ArrayList<>();
		userRepository.findAll().forEach((user)->appUsers.add(user));
		return appUsers;
	}
	
}
