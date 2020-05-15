package com.example.springjpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		/*
		 * phase 1
		 */
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)	// point spring datasource to your database
//			.withDefaultSchema()	//
//			.withUser(
//					User.withUsername("user")
//					.password("pass")
//					.roles("USER")
//			)
//			.withUser(
//					User.withUsername("admin")
//						.password("pass")
//						.roles("ADMIN")
//			);
		/*
		 * After setting up default schema
		 */
		auth.jdbcAuthentication()
		.dataSource(dataSource);
		/*
		 * 
		 */
		
		/*
		 * Work with schema you already had?
		 */
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select username, password, enabled "
//				+ "from users "
//				+ "where username = ?")
//		.authoritiesByUsernameQuery("select username, authority "
//				+ "from authorities "
//				+ "where username = ?");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/h2-console/**").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN", "USER")
			.antMatchers("/").permitAll()
			.and().formLogin()
			.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
			.and().headers().frameOptions().sameOrigin();

	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	
}
