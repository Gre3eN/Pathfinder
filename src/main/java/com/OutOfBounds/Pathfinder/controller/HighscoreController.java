package com.OutOfBounds.Pathfinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OutOfBounds.Pathfinder.model.Highscore;
import com.OutOfBounds.Pathfinder.service.HighscoreService;

@RestController
@RequestMapping("/highscore")
public class HighscoreController {
	@Autowired
	HighscoreService highscoreService;

	@GetMapping("/{amount}")
	public List<Highscore> getHighscores(@PathVariable int amount) {
		return highscoreService.getHighscores(amount);
	}
}
