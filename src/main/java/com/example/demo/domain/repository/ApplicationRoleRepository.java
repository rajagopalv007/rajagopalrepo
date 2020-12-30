package com.example.demo.domain.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.ApplicationRole;
import com.example.demo.domain.ApplicationUser;

public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, UUID> {
	public ApplicationRole findByRoleName(String roleName);
}
