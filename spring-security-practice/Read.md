## AuthenticationManager
- The way to config is to do things with authenticationManager, it has authentica() method to return a result.
- You work with it via a builder class AuthenticationManagerBuilder.
- Spring security app has a class call configure which take AuthMgrBdr.
- To config, extend that class and override config method
```java
@EnableWebSecurity 	// --> important
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("UserName")
			.password("password")
			.roles("USER")
			;
	}
}

```

How to set a password encoder?
Just expose an @Bean of type PasswordEncoder!

```java
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
```
use and() method to return an object as inMemoryAuthentication to create multiple users

Complete!
```java
@EnableWebSecurity 	// --> important
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("UserName")
			.password("password")
			.roles("USER")
			.and()
			.withUser("foo")
			.password("foo")
			.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
```
## Config Authorization
Default API behavior
| API | roles|
|-----|------|
|/|all (unauthenticated)|
|/user| User and Admin roles|
|/admin| admin|

**HttpSecurity** is used to implement restriction on roles

```
.hasAnyRole("User","Admin", "SomeRole") // config roles

.hasRole("User") // config a role
```

```java
@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll() // public url
			.antMatchers("/admin")		// match a url to match specific role
			.hasRole("ADMIN")
			.antMatchers("/user")
			.hasAnyRole("USER", "ADMIN") // manually allow admin to access user rout
			.and().formLogin();		
	}
``



