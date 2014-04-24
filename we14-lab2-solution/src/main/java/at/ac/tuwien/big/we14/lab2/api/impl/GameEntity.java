package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet;

public class GameEntity {
	protected static Logger log = Logger.getLogger(GameEntity.class);
	// Key = CategoryName meistens 5, List<Question> = questioncount viele Fragen pro Key
	private GameBean gamebean;
	
	private int getRoundNumber(){
		log.info("gamesize: "+gamebean.getGame().size()+" roundcount: "+gamebean.getRoundCount());
		return gamebean.getGame().size()-gamebean.getRoundCount();
	}
	
	public void setGame(HashMap<String, List<Question>> game, GameBean bean) {
		//Game = game;
		this.gamebean = bean;
		gamebean.setRoundsQuantity(game.size());
		
		gamebean.setRoundCount(game.size());
		
		HashMap<String, Round> g = new HashMap<String, Round>();
		for(Entry<String, List<Question>> entry : game.entrySet()) {
		   gamebean.getRoundList().add(entry.getKey());//ACHTUNG  POINTER?
		   //gamebean.setRoundList(gamebean.getRoundList().add(entry.getKey()));
		   //roundList.add(entry.getKey());
		   gamebean.setQuestionsQuantity(entry.getValue().size());
		   Round r = new Round(entry.getValue());
		  
		   g.put(entry.getKey(), r);
		   
		}
				
		gamebean.setGame(g);
		
		log.info("set game sagt hallo");
	}

	public boolean hasNextRound(GameBean bean){
		this.gamebean = bean;
		log.info("has next round aufgerufen");
		if(gamebean.getRoundCount()<=0){
			return false;
		}else{
			return true;
		}
	}
	
	public Round nextRound(GameBean bean){
		this.gamebean = bean;
		int count = gamebean.getRoundsQuantity();
		Iterator<String> it = gamebean.getRoundList().iterator();
		log.info("nextRound sagt hallo");
		log.info("roundlistiterator hasNext?"+it.hasNext());
		while(it.hasNext())
		{
			log.info("nextround whileschleife passiert");
		    String Runde = it.next();
		    log.info("count("+count+") ?=? roundCount("+gamebean.getRoundCount());
		    if(count == gamebean.getRoundCount()){
		    	log.info("roundcount ist "+gamebean.getRoundCount());
		    	gamebean.setRoundCount(gamebean.getRoundCount()-1);
		    	log.info("Runde in nextRound: "+Runde);
		    	//currentRound = Game.get(Runde); alt
		    	gamebean.setCurrentRoundObj(gamebean.getGame().get(Runde));
		    	log.info("Aktuelles rundenobject: "+gamebean.getGame().get(Runde));
		    	gamebean.setCurrentRound(this.getRoundNumber());
		    	return gamebean.getGame().get(Runde);
		    }
		    count--;
		}
		log.info("Rueckgabe hat einen Fehler!Aktuelle Rudne wird zur�ckgegeben");
		return new Round(null);
	}

	public Round thisRound(GameBean bean){
		this.gamebean = bean;
		return gamebean.getCurrentRoundObj();
	}
	
	public GameBean getBean(){
		return gamebean;
	}
	
	public void validateQuestion(String player, int timeleft, String[] ticked, GameBean bean){
		HashMap<Choice, Boolean> gesetzehackerl = new HashMap<Choice,Boolean>();
		
		for(String s:ticked){
			log.info("parametervalues: "+ s);
			for(Choice c:bean.getCurrentQuestion().getAllChoices()){
				if(c.getId() == Integer.parseInt(s)){
					gesetzehackerl.put(c, true);
				}
			}
		}
		Answer ans = new SimpleAnswer();
		ans.setId(1);
		ans.setPlayer(player);
		ans.setTime(timeleft);
		ans.setTickedHackerl(gesetzehackerl);
		
		log.info("ist es wahr?: "+ans.validateWith(bean.getCurrentQuestion().getCorrectChoices()));
		
	}
	
	public void nextQuestion(Round r, GameBean bean){
		this.gamebean = bean;
		Question q = r.next();
		gamebean.setCurrentQuestion(q);
		log.info("n�chste Frage:"+q);
		log.info("n�chste Frage backtrace"+gamebean.getCurrentQuestion());
	}
}
