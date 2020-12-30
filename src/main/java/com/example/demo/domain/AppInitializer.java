package com.example.demo.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.repository.ApplicationRoleRepository;
import com.example.demo.service.UserService;

@Component
public class AppInitializer implements SmartApplicationListener {

	private static Logger logger = LogManager.getLogger();

	
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationRoleRepository roleRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("In AppInitializer");

		List<ApplicationUser> userList = userService.getAllUsers();
		if (CollectionUtils.isEmpty(userList)) {
			logger.info("Creating default user");
			ApplicationUser appUser = createDefaultUser();
			ApplicationRole readOnlyRole = createApplicationReadOnlyRole();
			ApplicationRole readWriteRole=createApplicationReadWriteRole();
			ApplicationRole adminRole=createApplicationAdminRole();
			appUser.getUserRoles().add(readOnlyRole);
			appUser.getUserRoles().add(readWriteRole);
			appUser.getUserRoles().add(adminRole);
			userService.createUser(appUser);
			//userRepository.save(appUser);
			//roleRepository.save(readOnlyRole);
			//userService.addRoleToUser(appUser.getUser_uuid(), readOnlyRole.getRole_uuid());
			//userService.addRoleToUser(appUser.getUser_uuid(), readWriteRole.getRole_uuid());
			//userService.addRoleToUser(appUser.getUser_uuid(), adminRole.getRole_uuid());
			logger.info("**Default user with roles created:** {}; Password: {}", appUser, appUser.getPlainPassword());
		} else {
			logger.info("Atleast one user already exists");
			for(ApplicationUser appUser:userList) {
				
				logger.info(appUser);
			}
		}
		
		logger.info("Done AppInitializer");

	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		logger.info("supportsEventType: {}", eventType.toString());

		if (ContextRefreshedEvent.class.isAssignableFrom(eventType)) {
			logger.info("supportsEventType: {},{}", eventType, true);
			return true;
		} else {
			return false;
		}
	}

	private ApplicationUser createDefaultUser() {
		ApplicationUser user1 = new ApplicationUser();
		
		String userName = "defaultUser";
		user1.setUserName(userName);
		
		user1.setPlainPassword("password-" + userName);
		user1.setEnabled(true);
		return user1;
	}

	private ApplicationUser createRandomUser() {
		ApplicationUser user1 = new ApplicationUser();
		
		String userName = RandomStringUtils.randomAlphabetic(3);
		user1.setUserName(userName);
		user1.setPlainPassword("password-" + userName);
		user1.setEnabled(true);
		return user1;
	}

	private ApplicationRole createApplicationReadOnlyRole() {
		ApplicationRole role = new ApplicationRole();
		
		role.setRoleName("readonly");
		return role;

	}

	private ApplicationRole createApplicationReadWriteRole() {
		ApplicationRole role = new ApplicationRole();
		
		role.setRoleName("readwrite");
		return role;
	}
	
	private ApplicationRole createApplicationAdminRole() {
		ApplicationRole role = new ApplicationRole();
		
		role.setRoleName("admin");
		return role;
	}

}
