package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
		
		for(Entry<Choice, Boolean> jedestickedhackerl : tickedhackerl.entrySet()) {
		    Choice c = jedestickedhackerl.getKey();
		    Boolean b = jedestickedhackerl.getValue();
		    for(Choice ch: correctChoices){
		    	  if(c.getId() == ch.getId()){
		    		  if(b == true){
		    			  this.isCorrect = true;
		    			  return true;		    			  
		    		  }else{
		    			  this.isCorrect = false;
		    			  return false;
		    		  }
		    		  
		    	  }else{
		    		  return false;
		    	  }
		    }
		}
		
		
		return false;
	}
	
	
}
