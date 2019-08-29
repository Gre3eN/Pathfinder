package com.OutOfBounds.Pathfinder.model;

public class Achievement {

	private String pointOfInterestId;

	private int value;

	public Achievement(String pointOfInterestId, int value) {
		setPointOfInterestId(pointOfInterestId);
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getPointOfInterestId() {
		return pointOfInterestId;
	}

	public void setPointOfInterestId(String pointOfInterestId) {
		this.pointOfInterestId = pointOfInterestId;
	}
}
