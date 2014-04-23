package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.GameGenerator;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionGenerator;

public class SimpleGameGenerator implements GameGenerator{

	private List<Category> categories = new ArrayList<Category>();
	private GameEntity gameEntity = new GameEntity();
	
	private Question question = new SimpleQuestion();
	private Category category = new SimpleCategory();
	
	private SimpleCategoryGenerator catGen = new SimpleCategoryGenerator();
	private QuestionGenerator questionGen = new SimpleQuestionGenerator();
	public SimpleGameGenerator(){}
	public SimpleGameGenerator(List<Category> categories, GameEntity gameEntity)
	{
		this.categories = categories;
		this.gameEntity = gameEntity;
	}
	
	@Override
	public void generateGame(int rounds, int questioncount) {
		HashMap<String, List<Question>> Game = new HashMap<String, List<Question>>();
		
		
		// dem Generator einen Liste von Kategorien zuweisen
		catGen = new SimpleCategoryGenerator(categories);        		
		// Neue zufällige Kategorie wählen, welche noch nicht war
		category = catGen.getCategory();
		
		// Dem Fragengenerator eine zufällige Kategorie zuweisen 
		questionGen = new SimpleQuestionGenerator(category);
		// Aus den möglichen Fragen einer Kategorie eine neue zufällige Frage wählen
		question = questionGen.getQuestion();
		
		
		for(int i = 0; i < rounds; i++)
		{
			for(int j = 0; i < questioncount; j++)
			{
				
			}
		}
		
	}

}