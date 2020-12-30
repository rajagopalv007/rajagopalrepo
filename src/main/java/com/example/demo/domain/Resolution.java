package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user_resolutions")
public class Resolution {

	@Id
	@Column(name="res_uuid")
	@JsonProperty(access = Access.READ_ONLY)
	private UUID res_uuid=UUID.randomUUID();
	@Column(name="resolution")
	private String resolution;
	@ManyToOne
	@JoinColumn(name="fk_uid")
	private ApplicationUser createdBy;
	
	public UUID getRes_uuid() {
		return res_uuid;
	}
	public void setRes_uuid(UUID res_uuid) {
		this.res_uuid = res_uuid;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public ApplicationUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(ApplicationUser createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
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
		Resolution other = (Resolution) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Resolution [res_uuid=" + res_uuid + ", resolution=" + resolution + ", createdBy=" + createdBy + "]";
	}
	
	
	
}
