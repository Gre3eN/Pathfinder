package com.OutOfBounds.Pathfinder.model;

public class PointOfInterestDistance implements Comparable<PointOfInterestDistance> {

	private String pointOfInterestId;

	private double distance;

	public PointOfInterestDistance(String pointOfInterestId, double distance) {
		setPointOfInterestId(pointOfInterestId);
		setDistance(distance);
	}

	public String getPointOfInterestId() {
		return pointOfInterestId;
	}

	public void setPointOfInterestId(String pointOfInterestId) {
		this.pointOfInterestId = pointOfInterestId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(PointOfInterestDistance o) {
		return Double.valueOf(distance).compareTo(o.getDistance());
	}
}
