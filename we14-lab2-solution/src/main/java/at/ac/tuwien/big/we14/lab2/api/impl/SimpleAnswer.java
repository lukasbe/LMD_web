package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Choice;

public class SimpleAnswer implements Answer{
	
	//protected static Logger log = Logger.getLogger(SimpleAnswer.class);
	
	private int Id;
	private Boolean isCorrect = null;
	private String player;
	private int computertime = -1;
	ArrayList<Choice> tickedhackerl;
	List<Choice> correctChoices;
	

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
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPlayer(String player) {
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
			ArrayList<Choice> tickedhackerl) {		
		this.tickedhackerl = tickedhackerl;
	}

	@Override
	public Boolean validateWith(List<Choice> correctChoices) {
		if(tickedhackerl == null){
			//log.info("FEHLER!! Zuerst die Hackerl in setTickedHackerl initialisieren.");
			return null;
		}
		
		//log.info("validate");
		
		this.correctChoices = correctChoices;
		
		boolean answeredCorrectly = false;
		
		//log.info("validate, boolean initialisiert");
		
		if(!(tickedhackerl.size() == correctChoices.size())){
			//log.info("vergleich Gr��e false");
			return false;
		}
			
		for(Choice c : tickedhackerl){
				
			if(correctChoices.contains(c)){
				//log.info("korrekte Antwort ausgew�hlt");
				answeredCorrectly = true;
			}
			else{
				//log.info("Antwort ausgew�hlt, die nicht korrekt ist: " + c);
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
	public int getComputerTime(List<Choice> correctChoices){
		//log.info("computertime");
		if(computertime == -1){
			Random generator = new Random();
			
			this.computertime = generator.nextInt((int)correctChoices.get(0).getQuestion().getMaxTime());
			//log.info("computertime"+computertime);
			return computertime;
		}else{
			return computertime;
		}
	}
	
}
