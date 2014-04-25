package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we14.lab2.api.Category;
/**
 * 
 * @author Daniel Ramsauer 1226696
 * Diese Klasse stellt einen Kategorie Generator zur Verfügung.
 * Bei der ersten Instanzierung wird eine Kategorie zufällig festgelegt.
 * Bei jedem getCategory Aufruf, wird eine neue noch nicht verwendete Kategorie zurückgeliefert
 */
public class SimpleCategoryGenerator {

	List<Category> alreadyUsedCategory = new ArrayList<Category>();
	List<Category> categories = new ArrayList<Category>();
	public SimpleCategoryGenerator(){}
	
	public SimpleCategoryGenerator(List<Category> catergories)
	{
		this.categories = catergories;
	}
	
	public Category getCategory()
	{
		if(categories.isEmpty()) return null;
		
		List<Category> list = new ArrayList<Category>();

		for(Category c: categories)
		{
			// Falls die Category noch nicht verwendet wurde
			if(!alreadyUsedCategory.contains(c))
			{
				list.add(c);
			}
		}
		
		if(list.isEmpty())
		{
			return null;
		}
		
		Random r = new Random();
		
		Category c = list.get(r.nextInt(list.size()));
		alreadyUsedCategory.add(c);
		return c;
	}
}
