package com.OutOfBounds.Pathfinder.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.OutOfBounds.Pathfinder.model.ApplicationUser;
import com.OutOfBounds.Pathfinder.model.Highscore;

@Service
public class HighscoreService {

	private List<Highscore> buffer;
	private Map<String, Highscore> bufferUsername;

	public HighscoreService() {
		buffer = new ArrayList<>();
		bufferUsername = new HashMap<>();
	}

	public void updateBuffer(List<ApplicationUser> users) {
		buffer = users.stream().map(u -> new Highscore(u.getUsername(), u.getScore()))
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		for (int i = 0; i < buffer.size(); i++) {
			Highscore currentHighscore = buffer.get(i);
			currentHighscore.setPosition(i + 1);
			bufferUsername.put(currentHighscore.getUsername(), currentHighscore);
		}
	}

	public List<Highscore> getHighscores(int amount) {
		return buffer.stream().limit(amount).collect(Collectors.toList());
	}

	public Highscore getHighscoreByUsername(String username) {
		return bufferUsername.get(username);
	}
}
