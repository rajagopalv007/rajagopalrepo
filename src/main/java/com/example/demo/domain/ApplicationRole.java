package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Entity
@Table(name="application_roles")
public class ApplicationRole {
	@Id
	@Column(name="role_uuid")
	//@JsonProperty(access = Access.READ_ONLY)
	private UUID role_uuid=UUID.randomUUID();
	@Column(name="roleName")
	private String roleName;
	
	
	//private Set<ApplicationUser> users;
	
	
	public UUID getRole_uuid() {
		return role_uuid;
	}
	private void setRole_uuid(UUID role_uuid) {
		this.role_uuid = role_uuid;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role_uuid == null) ? 0 : role_uuid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationRole other = (ApplicationRole) obj;
		if (role_uuid == null) {
			if (other.role_uuid != null)
				return false;
		} else if (!role_uuid.equals(other.role_uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ApplicationRole [roleName=" + roleName + "]";
	}
	
	
}
