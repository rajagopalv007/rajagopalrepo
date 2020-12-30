package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.domain.ApplicationUser;

@Component("dbUserDetailsService")
public class DBUserDetailsService implements UserDetailsService {

	private static Logger logger = LogManager.getLogger();
	@Autowired
	private ApplicationUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("In loadUserByUsername() for {}",username);
		ApplicationUser appUser = userRepository.findByUserName(username);
		if(appUser==null) {
			throw new UsernameNotFoundException("Could not find user "+username+" in database");
		}
		User.UserBuilder userBuilder = User.builder().username(appUser.getUserName()).password(appUser.getEncryptedPassword());
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		appUser.getUserRoles().forEach((userRole) -> roles.add(new SimpleGrantedAuthority(userRole.getRoleName())));
		userBuilder.authorities(roles);
		UserDetails userDetails=userBuilder.build();
		logger.info(userDetails);
		return userDetails;
	}

}
