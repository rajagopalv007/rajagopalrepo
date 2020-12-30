package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Resolution;
import com.example.demo.domain.repository.ResolutionRepository;

@Component
public class ResolutionService {
	private static Logger logger = LogManager.getLogger();
	@Autowired
	private ResolutionRepository resolutionRepository;

	@Transactional
	public void createResolution(Resolution resolution) {
		logger.info("Creating resolution {}",resolution);
		
		resolutionRepository.save(resolution);
	}
	
	@Transactional
	public List<Resolution> getAllResolutionsForCurrentUser(UUID userUUID) {
		
		
		return resolutionRepository.findResolutionsByOwner(userUUID);
	}
}
