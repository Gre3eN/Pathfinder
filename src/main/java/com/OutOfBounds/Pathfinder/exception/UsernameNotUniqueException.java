package com.OutOfBounds.Pathfinder.exception;

@SuppressWarnings("serial")
public class UsernameNotUniqueException extends Exception {
	public UsernameNotUniqueException(String username) {
		super(String.format("Username %s already taken!", username));
	}
}
