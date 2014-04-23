package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameEntity {

	// Key = CategoryName, List<Question> = questioncount viele Fragen pro Key
	HashMap<String, List<Question>> Game = new HashMap<String, List<Question>>();

	public HashMap<String, List<Question>> getGame() {
		return Game;
	}

	public void setGame(HashMap<String, List<Question>> game) {
		Game = game;
	}
	
}
