package com.example.demo.domain.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.ApplicationRole;
import com.example.demo.domain.ApplicationUser;

@Component
public  class ApplicationUserRepositoryCustomImpl implements ApplicationUserRepositoryCustom {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	public EntityManager em;
	
	
	@Override
	public void addRoleToUser(UUID userUUID, UUID additionalRolesId) {
		logger.info("In addRoleToUser()");
		ApplicationUser appUser=em.find(ApplicationUser.class, userUUID);
		if(appUser!=null) {
			Set<ApplicationRole> existingRoles=appUser.getUserRoles();
			if(CollectionUtils.isEmpty(existingRoles)) {
				existingRoles=new HashSet<>();
				appUser.setUserRoles(existingRoles);
			}
			//for(UUID roleId:additionalRolesIds) {
				ApplicationRole appRole=em.find(ApplicationRole.class, additionalRolesId);
				if(appRole==null) {
					throw new RuntimeException("Role with id "+additionalRolesId+" not found");
				}
				existingRoles.add(appRole);
			//}			
		}
		else {
			throw new RuntimeException("User with id "+userUUID+" not found");
		}
	}

	

}
