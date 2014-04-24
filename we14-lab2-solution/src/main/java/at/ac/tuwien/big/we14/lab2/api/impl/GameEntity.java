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
		log.info("Rueckgabe hat einen Fehler!Aktuelle Rudne wird zurückgegeben");
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
		//HashMap<Choice, Boolean> gesetzehackerl = new HashMap<Choice,Boolean>();
		ArrayList<Choice> gesetzehackerlliste = new ArrayList<Choice>();
		
		boolean playerright, cpuplayerright = false;
		int cputime, playertime = 30;
		if(ticked == null){
			playerright = false;
			Answer a = new SimpleAnswer();
			cpuplayerright = a.getIsComputerCorrect();
		
		}else{
			for(String s:ticked){
				log.info("parametervalues: "+ s);
				for(Choice c:bean.getCurrentQuestion().getAllChoices()){
					if(c.getId() == Integer.parseInt(s)){
						gesetzehackerlliste.add(c);
						
						//gesetzehackerl.put(c, true);
					}
				}
			}
			Answer ans = new SimpleAnswer();
			ans.setId(1);
			ans.setPlayer(player);
			ans.setTime(timeleft);
			ans.setTickedHackerl(gesetzehackerlliste);
			log.info("ist es wahr?: "+ans.validateWith(bean.getCurrentQuestion().getCorrectChoices()));
			cpuplayerright = ans.getIsComputerCorrect();
			//cputime = ans.getComputerTime(bean.getCurrentQuestion().getCorrectChoices());
			playerright = ans.validateWith(bean.getCurrentQuestion().getCorrectChoices());
			
		}
		//ArrayList playerSummary = new ArrayList<Boolean>();
		
		gamebean.getPlayer1RoundSummary().add(playerright);
		gamebean.getPlayer2RoundSummary().add(cpuplayerright);
	}
	
	public void nextQuestion(Round r, GameBean bean){
		this.gamebean = bean;
		Question q = r.next();
		gamebean.setCurrentQuestion(q);
		log.info("nächste Frage:"+q);
		log.info("nächste Frage backtrace"+gamebean.getCurrentQuestion());
	}
	
public void determineRounds(GameBean bean){
		
		int player1Points = 0;
		int player2Points = 0;
		
		for(boolean b : bean.getPlayer1RoundSummary()){
			 if(b == true){
				 player1Points++;
			 }
			 else{
				 player1Points--;
			 }
		}
		
		for(boolean b : bean.getPlayer2RoundSummary()){
			 if(b == true){
				 player2Points++;
			 }
			 else{
				 player2Points--;
			 }
		}
		if(player1Points < player2Points){
			bean.setCurrentRoundWinner(bean.getPlayer2());
		} else if(player1Points > player2Points){
			bean.setCurrentRoundWinner(bean.getPlayer1());
		} else{
			if(bean.getPlayer1RoundTime() < bean.getPlayer2RoundTime()){
				bean.setCurrentRoundWinner(bean.getPlayer1());	
			} else if(bean.getPlayer1RoundTime() > bean.getPlayer2RoundTime()){
				bean.setCurrentRoundWinner(bean.getPlayer2());
			}else{
				bean.setCurrentRoundWinner("Niemand");
				
			}
		}
	}
	
}
