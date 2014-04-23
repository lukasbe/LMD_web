package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionGenerator;

public class SimpleQuestionGenerator implements QuestionGenerator{

	private Category category = null;
	private List<Question> alreadyAskedQuestionList = new ArrayList<Question>();
	
	public SimpleQuestionGenerator()
	{
		
	}
	
	public SimpleQuestionGenerator(Category cat)
	{
		this.category = cat;
		alreadyAskedQuestionList = null;
	}
	
	@Override
	public Question getQuestion() {
		if(category == null) return null;
		List<Question> list = new ArrayList<Question>();
		
		for(Question q: category.getQuestions())
		{
			// Falls die Frage noch nicht gestellt wurde
			if(!alreadyAskedQuestionList.contains(q))
			{
				
				list.add(q);
			}
		}
		
		if(list.isEmpty())
		{
			return null;
		}
		
		Random r = new Random();
		Question q = list.get(r.nextInt(list.size()));
		alreadyAskedQuestionList.add(q);
		return q;
	}

	@Override
	public Category getCategory() {
		return category;
	}

}
