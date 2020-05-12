package com.example.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPracticeApplication.class, args);
	}

}
/*
 * Filters : intercept every request before it comes to servlets 
 * Spring security default behavior when you add it to the classpath
 *  adds mandatory authentication for URLs,
 *  adds login form,
 *  handles login error
 *  handles login error
 *  create a user and sets a default password
 * 
 * create default user manually
 *  spring.security.user.name=foo
 *	spring.security.user.password=foo
 * 
 * 
 *
 *
 */