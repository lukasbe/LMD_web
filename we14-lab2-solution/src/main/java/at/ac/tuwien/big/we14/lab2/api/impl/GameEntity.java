package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameEntity {

	// Key = CategoryName meistens 5, List<Question> = questioncount viele Fragen pro Key
	private HashMap<String, List<Question>> Game = new HashMap<String, List<Question>>();
	private List<String> roundList = new ArrayList<String>();
	private int roundCount = 0;
	private int questionCount = 0;

	public void setGame(HashMap<String, List<Question>> game) {
		Game = game;
	}

	public boolean hasNextRound(){
		
		return false;
	}
	
	public Round nextRound(){
		
		return null;
	}

	
	
}
