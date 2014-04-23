package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameEntity {

	// Key = CategoryName meistens 5, List<Question> = questioncount viele Fragen pro Key
	private HashMap<String, List<Question>> Game = new HashMap<String, List<Question>>();
	private List<String> categoriesList = new ArrayList<String>();
	
	public HashMap<String, List<Question>> getGame() {
		return Game;
	}

	public void setGame(HashMap<String, List<Question>> game) {
		Game = game;
	}
	
	public int getRoundQuantity(){
		return Game.size();
	}
	
	public int getQuestionQuantity(){
		return 0;
	}

	public List<String> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<String> categoriesList) {
		this.categoriesList = categoriesList;
	}
	
}
