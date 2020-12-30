package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ApplicationRole;
import com.example.demo.domain.ResponseBean;
import com.example.demo.service.RoleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/roleManagement")
public class ApplicationRoleController {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "OK";
	}

	@RequestMapping(value = "/roles/", method = RequestMethod.GET, produces = { "application/json" })
	@ApiOperation(value="Get all roles",authorizations = {@Authorization(value="basicauth")})
	public ResponseEntity<ResponseBean> getAllUsers() {
		try {
			List<ApplicationRole> appRoles = roleService.getAllRoles();					
			ResponseBean response = new ResponseBean("OK", null, appRoles);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ResponseBean response = new ResponseBean("Failed", "Exception in getting roles");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

}
