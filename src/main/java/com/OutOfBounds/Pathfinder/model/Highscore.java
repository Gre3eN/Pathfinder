package com.OutOfBounds.Pathfinder.model;

public class Highscore implements Comparable<Highscore> {

	private int position;

	private String username;

	private int score;

	public Highscore() {
	}

	public Highscore(String username, Integer score) {
		setUsername(username);
		setScore(score);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Highscore o) {
		return Integer.valueOf(score).compareTo(o.getScore());
	}
}
