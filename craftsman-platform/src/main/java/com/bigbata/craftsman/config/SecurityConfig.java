/**
 * 
 */
package com.bigbata.craftsman.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.bigbata.craftsman.config.security.SecurityService;
import com.bigbata.craftsman.securityHandler.AuthFailureHandler;
import com.bigbata.craftsman.securityHandler.AuthSuccessHandler;
import com.bigbata.craftsman.securityHandler.HttpAuthenticationEntryPoint;

/**
 * 类说明:<br>
 * 创建时间: 2014年12月12日 下午3:44:11<br>
 * 
 * @author 刘岩松<br>
 * @email yansong.lau@gmail.com<br>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	@Autowired
	private AuthFailureHandler authFailureHandler;
	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	SecurityService securityService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(securityService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(
						authenticationEntryPoint
								.registerLoginUrl("/web/login/form"))
				.and()
				.authorizeRequests()
				.antMatchers("/web/login", "/web/login/form", "/web/logout",
						"/web/signup", "/web/about").permitAll()
				.antMatchers("/web/**", "/api/**").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().formLogin()
				.loginPage("/web/login/form").loginProcessingUrl("/web/login")
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.failureUrl("/web/login/form?error").and().logout()
				.logoutUrl("/web/logout").permitAll();
	}
}
