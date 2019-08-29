package com.OutOfBounds.Pathfinder.service;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.OutOfBounds.Pathfinder.repository.UserRepostirory;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private UserRepostirory userRepo;

	public UserDetailsServiceImp(UserRepostirory userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(user.getUsername(), user.getPassword(), emptyList());
	}
}
