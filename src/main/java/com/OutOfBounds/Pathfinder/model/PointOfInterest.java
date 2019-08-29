package com.OutOfBounds.Pathfinder.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public class PointOfInterest {

	@Id
	private String id;

	private final BigDecimal lat, lng;

	private boolean isActive;

	public PointOfInterest(BigDecimal lat, BigDecimal lng) {
		this.lat = lat;
		this.lng = lng;
		isActive = true;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
