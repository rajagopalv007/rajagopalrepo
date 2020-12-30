package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

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
import com.example.demo.domain.ResponseBean;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/userManagement")
public class ApplicationUserController {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "OK";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value="Get all users",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> getAllUsers() {
		try {
			List<ApplicationUser> appUsers = userService.getAllUsers();
			ResponseBean response = new ResponseBean("OK", null, appUsers);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean("Failed", "Exception in getting users");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/users/simple", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value="Create user without roles",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> createSimpleUser(@RequestBody ApplicationUser appUser) {
		try {
			userService.createSimpleUser(appUser);
			ResponseBean response = new ResponseBean("Created", null);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean("Failed", "Exception in creating users");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT, produces = { "application/json" })
	@ApiOperation(value="Update existing user with existing role",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> addRoleToUser(@PathVariable String userId, @RequestParam String roleId) {
		try {
			userService.addRoleToUser(UUID.fromString(userId), UUID.fromString(roleId));
			ResponseBean response = new ResponseBean("Updated", null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean("Failed", "Exception in updating users");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
