package com.example.demo.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.ApplicationUser;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,UUID>,ApplicationUserRepositoryCustom {

	
	
	public ApplicationUser findByUserName(String userName);

	

}
