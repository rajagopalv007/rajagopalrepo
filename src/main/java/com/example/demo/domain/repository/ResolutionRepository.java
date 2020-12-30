package com.example.demo.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Resolution;

public interface ResolutionRepository extends CrudRepository<Resolution, UUID> {
	
	@Query(value = "from Resolution r where r.createdBy.user_uuid =:owner")
	public List<Resolution> findResolutionsByOwner(@Param("owner")UUID owner);
}
