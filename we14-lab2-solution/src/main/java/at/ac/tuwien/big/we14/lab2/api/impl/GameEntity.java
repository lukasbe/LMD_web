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
	
	public HashMap<String, List<Question>> getGame() {
		return Game;
	}

	public void setGame(HashMap<String, List<Question>> game) {
		Game = game;
	}

	public List<String> getRoundList() {
		return roundList;
	}

	public void setRoundList(List<String> roundList) {
		this.roundList = roundList;
	}
	
	public boolean hasNext()
	{
		
		return true;
	}
	public int getRoundCount() {
		return roundCount;
	}

	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}

	public int getQuestionCount() {
		return questionCount++;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public List<Question> next()
	{
		
		return null;
	}
	
	public List<Question> getNextRound()
	{
		return null;
	}
	
	public void iterateQuestionCount()
	{
		
	}
	
}
