package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "application_users")
public class ApplicationUser {

	@Id
	@Column(name="user_uuid")
	//@JsonProperty(access = Access.READ_ONLY)
	private UUID user_uuid=UUID.randomUUID();
	@Column(name="userName")
	private String userName;
	@Column(name="password")
	@JsonIgnore
	private String encryptedPassword;
	@Column(name="enabled")
	@Convert(converter = BooleanToStringConvertor.class)
	private boolean enabled=true;
	@Transient
	@JsonProperty(access = Access.WRITE_ONLY)
	private String plainPassword;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinTable(name = "application_user_roles", joinColumns = {
			@JoinColumn(name = "user_uuid") }, inverseJoinColumns = { @JoinColumn(name = "role_uuid") })
	@JsonProperty(access = Access.READ_ONLY)
	private Set<ApplicationRole> userRoles = new HashSet<>();

	public UUID getUser_uuid() {
		return user_uuid;
	}

	private void setUser_uuid(UUID user_uuid) {
		this.user_uuid = user_uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String password) {
		this.encryptedPassword = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ApplicationRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<ApplicationRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_uuid == null) ? 0 : user_uuid.hashCode());
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
		ApplicationUser other = (ApplicationUser) obj;
		if (user_uuid == null) {
			if (other.user_uuid != null)
				return false;
		} else if (!user_uuid.equals(other.user_uuid))
			return false;
		return true;
	}

	
	public void addRoleToUser(ApplicationRole role) {
		this.userRoles.add(role);
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	@Override
	public String toString() {
		return "ApplicationUser [user_uuid=" + user_uuid + ", userName=" + userName + ", userRoles=" + userRoles + "]";
	}
	
	
	
}
