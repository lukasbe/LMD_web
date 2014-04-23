package at.ac.tuwien.big.we14.lab2.api.impl;

import at.ac.tuwien.big.we14.lab2.api.Answer;

public class SimpleAnswer implements Answer{
	
	private int Id;
	private Boolean isCorrect;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
