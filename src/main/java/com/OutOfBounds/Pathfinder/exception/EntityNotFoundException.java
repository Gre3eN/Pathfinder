package com.OutOfBounds.Pathfinder.exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends Exception {
	public EntityNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
