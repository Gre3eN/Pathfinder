package com.OutOfBounds.Pathfinder.security;

import static com.OutOfBounds.Pathfinder.security.SecurityConstants.EXPIRATION_TIME;
import static com.OutOfBounds.Pathfinder.security.SecurityConstants.HEADER_STRING;
import static com.OutOfBounds.Pathfinder.security.SecurityConstants.SECRET;
import static com.OutOfBounds.Pathfinder.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// provides /login endpoint
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
		log.info("login attempt");
		try {
			ApplicationUser creds = new ObjectMapper().readValue(request.getInputStream(),
					ApplicationUser.class);

			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>())); // authorities

		} catch (Exception e) {
			log.error("catching them all {}", e.getCause());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException {
		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(HMAC512(SECRET.getBytes()));

		res.addHeader("access-control-expose-headers", HEADER_STRING);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}
