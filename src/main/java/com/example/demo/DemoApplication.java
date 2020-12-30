package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
public class DemoApplication extends WebSecurityConfigurerAdapter {

	@Bean
	PasswordEncoder getPasswordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	@Qualifier("dbUserDetailsService")
	UserDetailsService dbUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(dbUserDetailsService).passwordEncoder(getPasswordEncode());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/h2-console/**").permitAll().antMatchers("**/health/**")
				.permitAll().antMatchers("**/swagger-ui*/**").permitAll().antMatchers(HttpMethod.POST,"**/simple*/**")
				.hasAuthority(
						"readwrite").antMatchers(HttpMethod.PUT,"**/simple*/**").hasAuthority("readwrite").antMatchers(HttpMethod.POST)
				.hasAuthority("admin").antMatchers(HttpMethod.PUT).hasAuthority("admin").anyRequest()
				.hasAuthority("readonly").and().httpBasic();
		http.headers().frameOptions().disable();
		// .userDetailsService(dbUserDetailsService);
		// http.authorizeRequests().anyRequest().permitAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
