package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet;

public class SimpleAnswer implements Answer{
	
	protected static Logger log = Logger.getLogger(BigQuizServlet.class);
	
	private int Id;
	private Boolean isCorrect = null;
	private int time;
	private String player;
	private int computertime;
	HashMap<Choice, Boolean> tickedhackerl;
	ArrayList<Choice> correctChoices;
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public void setTime(int time) {
		// TODO Auto-generated method stub
		this.time = time;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayer(String player) {
		// TODO Auto-generated method stub
		this.player = player;
	}

	@Override
	public String getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public Boolean getIsCorrect(Boolean correct) {
		// TODO Auto-generated method stub
		return isCorrect;
	}

	@Override
	public void setTickedHackerl(
			HashMap<Choice, Boolean> tickedhackerl) {		
		this.tickedhackerl = tickedhackerl;
	}

	@Override
	public Boolean validateWith(ArrayList<Choice> correctChoices) {
		if(tickedhackerl == null){
			log.info("FEHLER!! Zuerst die Hackerl in setTickedHackerl initialisieren.");
			return null;
		}
		
		this.correctChoices = correctChoices;
		
		boolean answeredCorrectly = false;
		
		if(!(tickedhackerl.keySet().size() == correctChoices.size()))
				return false;
		
		for(Choice c : tickedhackerl.keySet()){
				
			if(correctChoices.contains(tickedhackerl.get(c))){
				answeredCorrectly = true;
			}
			else{
				answeredCorrectly = false;
				break;
			}
			
		}
		
		return answeredCorrectly;
	}

	@Override
	public Boolean getIsComputerCorrect() {
		// TODO Auto-generated method stub
		Random random = new Random();
	    return random.nextBoolean();
	}
	@Override
	public int getComputerTime(ArrayList<Choice> correctChoices){
		if(computertime == -1){
			Random generator = new Random();
			
			this.computertime = generator.nextInt((int)correctChoices.get(0).getQuestion().getMaxTime());
			return computertime;
		}else{
			return computertime;
		}
	}
	
}
