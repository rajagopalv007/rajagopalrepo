package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ApplicationUser;
import com.example.demo.domain.Resolution;
import com.example.demo.domain.ResponseBean;
import com.example.demo.service.ResolutionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/resolutionManagement")
public class ResolutionController {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	private ResolutionService resolutionService;

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "OK";
	}

	@RequestMapping(value = "/resolutions", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value="Get all resolutions for the current user",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> getAllResolutionsForCurrentUser(@RequestParam("ownerId") String ownerId) {
		try {
			List<Resolution> resolutions = resolutionService.getAllResolutionsForCurrentUser(UUID.fromString(ownerId));
			ResponseBean response = new ResponseBean("OK", null, resolutions);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			String errorMessage="Exception in getting resolutions."+ExceptionUtils.getStackTrace(e);
			logger.error(errorMessage);
			ResponseBean response = new ResponseBean("Failed", "Exception in getting resolutions");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/resolutions/simple", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value="Create a new resolution",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> createResolution(@RequestBody Resolution resolution) {
		try {
			resolutionService.createResolution(resolution);
			ResponseBean response = new ResponseBean("Created", null);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			String errorMessage="Exception in creating resolution."+ExceptionUtils.getStackTrace(e);
			logger.error(errorMessage);
			ResponseBean response = new ResponseBean("Failed", "Exception in creating resolution");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT, produces = { "application/json" })
	@ApiOperation(value="Update existing user with existing role",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> addRoleToUser(@PathVariable String userId, @RequestParam String roleId) {
		try {
			resolutionService.addRoleToUser(UUID.fromString(userId), UUID.fromString(roleId));
			ResponseBean response = new ResponseBean("Updated", null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean("Failed", "Exception in updating users");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/

}
