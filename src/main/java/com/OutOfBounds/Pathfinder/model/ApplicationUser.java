package com.OutOfBounds.Pathfinder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;

import com.OutOfBounds.Pathfinder.exception.EntityNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApplicationUser {
	@Id
	private String id;
	private String username;
	private String password;

	private List<Achievement> achievements = new ArrayList<>();
	private Map<String, PointOfInterest> pointOfInterests = new HashMap<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public void addAchievement(Achievement achievement) {
		achievements.add(achievement);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScore() {
		return achievements.stream().map(a -> a.getValue()).reduce(0, Integer::sum);
	}

	public List<PointOfInterest> getPointOfInterests() {
		return new ArrayList<>(pointOfInterests.values());
	}

	public void setPointOfInterests(List<PointOfInterest> pointOfInterests) {
		this.pointOfInterests = pointOfInterests.stream()
				.collect(Collectors.toMap(PointOfInterest::getId, p -> p));
	}

	@JsonIgnore
	public void setPointOfInterestInactive(String id) throws EntityNotFoundException {
		try {
			pointOfInterests.get(id).setActive(false);
		} catch (NullPointerException ex) {
			throw new EntityNotFoundException(
					String.format("PointOfInterest with Id %s couldn't be found!", id));
		}

	}
}
