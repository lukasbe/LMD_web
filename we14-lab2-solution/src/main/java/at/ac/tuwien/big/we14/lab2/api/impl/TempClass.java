package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.GameGenerator;
import at.ac.tuwien.big.we14.lab2.api.Question;

public class TempClass {

	private List<Category> categories;
	private GameGenerator gameGen;
	public TempClass(List<Category> categories)
	{
		this.categories = categories;
		gameGen = new SimpleGameGenerator(categories);
	}
	
	public void testGenerator()
	{
		for(int i = 0; i < 5; i++)
		{
			
			HashMap<String, List<Question>> list = gameGen.generateGame(5, 3);
			for (Entry<String, List<Question>> entry : list.entrySet()) {
			    String key = entry.getKey();
			    List<Question> value = entry.getValue();
			}
		}
	}
}
