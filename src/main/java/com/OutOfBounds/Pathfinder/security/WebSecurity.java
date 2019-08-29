package com.OutOfBounds.Pathfinder.security;

import static com.OutOfBounds.Pathfinder.security.SecurityConstants.POINT_OF_INTEREST_ADD_URL;
import static com.OutOfBounds.Pathfinder.security.SecurityConstants.POINT_OF_INTEREST_ALL_URL;
import static com.OutOfBounds.Pathfinder.security.SecurityConstants.SIGN_UP_URL;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.OutOfBounds.Pathfinder.service.UserDetailsServiceImp;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImp userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public WebSecurity(UserDetailsServiceImp userDetailsService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
				.antMatchers(POINT_OF_INTEREST_ALL_URL).permitAll()
				.antMatchers(POINT_OF_INTEREST_ADD_URL).permitAll().anyRequest().authenticated()
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				final CorsConfiguration configuration = new CorsConfiguration();
				configuration.setAllowedOrigins(Collections.unmodifiableList(Arrays.asList("*")));
				configuration.setAllowedMethods(Collections.unmodifiableList(
						Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")));
				configuration.setAllowCredentials(true);
				configuration.setAllowedHeaders(Collections.unmodifiableList(
						Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
				return configuration;
			}
		};
	}

}
