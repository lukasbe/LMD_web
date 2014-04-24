package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet;

public class GameEntity {
	protected static Logger log = Logger.getLogger(GameEntity.class);
	// Key = CategoryName meistens 5, List<Question> = questioncount viele Fragen pro Key
	private HashMap<String, Round> Game = new HashMap<String, Round>();
	private List<String> roundList = new ArrayList<String>();
	private Round currentRound = new Round(null);
	
	private GameBean gamebean;
	
	private int getRoundNumber(){
		return Game.size()-gamebean.getRoundCount();
	}
	
	public void setGame(HashMap<String, List<Question>> game) {
		//Game = game;
		gamebean = new GameBean();
		gamebean.setRoundsQuantity(game.size());
		
		gamebean.setRoundCount(game.size());
		
		for(Entry<String, List<Question>> entry : game.entrySet()) {
		   
		   roundList.add(entry.getKey());
		   gamebean.setQuestionsQuantity(entry.getValue().size());
		   Round r = new Round(entry.getValue());
		   Game.put(entry.getKey(), r);

		}
		
		log.info("set game sagt hallo");
	}

	public boolean hasNextRound(){
		log.info("has next round aufgerufen");
		if(gamebean.getRoundCount()<=0){
			return false;
		}else{
			return true;
		}
	}
	
	public Round nextRound(){
		int count = gamebean.getRoundsQuantity();
		Iterator<String> it = roundList.iterator();
		log.info("nextRound sagt hallo");
		log.info("roundlistiterator hasNext?"+it.hasNext());
		while(it.hasNext())
		{
			log.info("nextround whileschleife passiert");
		    String Runde = it.next();
		    log.info("count("+count+") ?=? roundCount("+gamebean.getRoundCount());
		    if(count == gamebean.getRoundCount()){
		    	gamebean.setRoundCount(gamebean.getRoundCount()--);
		    	log.info("Runde in nextRound: "+Runde);
		    	currentRound = Game.get(Runde);
		    	gamebean.setCurrentRound(this.getRoundNumber());
		    	gamebean.setPlayer1("Dagobert");
		    	return Game.get(Runde);
		    }
		    count--;
		}
		log.info("Rueckgabe hat einen Fehler!Aktuelle Rudne wird zurückgegeben");
		return new Round(null);
	}

	public Round thisRound(){
		return currentRound;
	}
	
	public GameBean getBean(){
		return gamebean;
	}
	
	public void nextQuestion(Round r){
		
		Question q = r.next();
		gamebean.setCurrentQuestion(q);
		log.info("nächste Frage:"+q);
		log.info("nächste Frage backtrace"+gamebean.getCurrentQuestion());
	}

}
