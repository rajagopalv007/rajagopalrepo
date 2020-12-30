package com.example.demo.domain.repository;

import java.util.Set;
import java.util.UUID;

import com.example.demo.domain.ApplicationRole;

public interface ApplicationUserRepositoryCustom {
   //public void updateUserRoles(UUID userUUID,Set<ApplicationRole> additionalRoles);

public void addRoleToUser(UUID userUUID, UUID additionalRolesId);
}
