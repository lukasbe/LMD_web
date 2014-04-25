package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.GameGenerator;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionGenerator;

public class SimpleGameGenerator implements GameGenerator{

	private List<Category> categories = new ArrayList<Category>();
	
	private Question question = new SimpleQuestion();
	private Category category = new SimpleCategory();
	
	private SimpleCategoryGenerator catGen = new SimpleCategoryGenerator();
	private QuestionGenerator questionGen = new SimpleQuestionGenerator();
	
	protected static Logger log = Logger.getLogger(SimpleGameGenerator.class);
	
	public SimpleGameGenerator(){}
	
	public SimpleGameGenerator(List<Category> categories)
	{
		this.categories = categories;
	}
	
	@Override
	public LinkedHashMap<String, List<Question>> generateGame(int rounds, int questioncount) {
		if(categories.isEmpty()) return null;

		LinkedHashMap<String, List<Question>> Game = new LinkedHashMap<String, List<Question>>();
		List<Question> questionList = new LinkedList<Question>();
		
		// dem Generator einen Liste von Kategorien zuweisen
		catGen = new SimpleCategoryGenerator(categories);        		
		
		for(int i = 0; i < rounds; i++)
		{
			questionList = new LinkedList<Question>();
			// Neue zufällige Kategorie wählen, welche noch nicht war
			category = catGen.getCategory();
			log.error("----->>> SGG: CAT-Name: " + category.getName());
			// Dem Fragengenerator eine zufällige Kategorie zuweisen 
			questionGen = new SimpleQuestionGenerator(category);
			
			for(int j = 0; j < questioncount; j++)
			{
				// Aus den möglichen Fragen einer Kategorie eine neue zufällige Frage wählen
				question = questionGen.getQuestion();
				questionList.add(question);
			}
			Game.put(category.getName(), questionList);
			
		}
		for (Entry<String, List<Question>> entry : Game.entrySet()) {
			{
				String key = entry.getKey();
				log.error("GAMEGEN-Kategorie: " + key);
			}
		}
		return Game;
	}
}