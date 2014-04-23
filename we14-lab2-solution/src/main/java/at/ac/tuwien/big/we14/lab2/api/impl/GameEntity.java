package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet;

public class GameEntity {
	protected static Logger log = Logger.getLogger(GameEntity.class);
	// Key = CategoryName meistens 5, List<Question> = questioncount viele Fragen pro Key
	private HashMap<String, List<Question>> Game = new HashMap<String, List<Question>>();
	private List<String> roundList = new ArrayList<String>();
	private int roundSize;
	private int roundCount;

	public void setGame(HashMap<String, List<Question>> game) {
		Game = game;
		roundSize = game.size();
		roundCount = game.size();
		for(Entry<String, List<Question>> entry : Game.entrySet()) {
		   
		    roundList.add(entry.getKey());
		    

		}
		log.info("set game sagt hallo");
	}

	public boolean hasNextRound(){
		log.info("has next round aufgerufen");
		if(roundCount<=0){
			return false;
		}else{
			return true;
		}
	}
	
	public List<Question> nextRound(){
		int count = roundSize;
		Iterator<String> it = roundList.iterator();
		log.info("nextRound sagt hallo");
		log.info("roundlistiterator hasNext?"+it.hasNext());
		while(it.hasNext())
		{
			log.info("nextround whileschleife passiert");
		    String Runde = it.next();
		    log.info("count("+count+") ?=? roundCount("+roundCount);
		    if(count == roundCount){
		    	roundCount--;
		    	log.info("Runde in nextRound: "+Runde);
		    	return Game.get(Runde);
		    }
		    count--;
		}
		log.info("Rueckgabe hat einen Fehler!");
		return new ArrayList<Question>();
	}

	
	
}
