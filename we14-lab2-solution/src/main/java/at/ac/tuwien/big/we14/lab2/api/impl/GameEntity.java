package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import at.ac.tuwien.big.we14.lab2.api.Answer;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameEntity {
	private GameBean gamebean;
	
	private int getRoundNumber(){
		return gamebean.getGame().size()-gamebean.getRoundCount();
	}
	
	public void setGame(HashMap<String, List<Question>> game, GameBean bean) {
		this.gamebean = bean;
		gamebean.setRoundsQuantity(game.size());
		
		gamebean.setRoundCount(game.size());
		
		HashMap<String, Round> g = new HashMap<String, Round>();
		for(Entry<String, List<Question>> entry : game.entrySet()) {
		   gamebean.getRoundList().add(entry.getKey());

		   gamebean.setQuestionsQuantity(entry.getValue().size());
		   Round r = new Round(entry.getValue());
		  
		   g.put(entry.getKey(), r);
		   
		}
		gamebean.setGame(g);
	}

	public boolean hasNextRound(GameBean bean){
		this.gamebean = bean;
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
		while(it.hasNext())
		{
		    String Runde = it.next();
		    if(count == gamebean.getRoundCount()){
		    	gamebean.setRoundCount(gamebean.getRoundCount()-1);
		    	gamebean.setCurrentRoundObj(gamebean.getGame().get(Runde));
		    	gamebean.setCurrentRound(this.getRoundNumber());
		    	return gamebean.getGame().get(Runde);
		    }
		    count--;
		}
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
		ArrayList<Choice> gesetzehackerlliste = new ArrayList<Choice>();
		
		boolean playerright, cpuplayerright = false;
		int cputime = 30, playertime = 30;
		if(ticked == null){
			playerright = false;
			Answer a = new SimpleAnswer();
			cpuplayerright = a.getIsComputerCorrect();
		}else{
			for(String s:ticked){
				for(Choice c:bean.getCurrentQuestion().getAllChoices()){
					if(c.getId() == Integer.parseInt(s)){
						gesetzehackerlliste.add(c);
					}
				}
			}
			Answer ans = new SimpleAnswer();
			ans.setId(1);
			ans.setPlayer(player);
			ans.setTime(timeleft);
			ans.setTickedHackerl(gesetzehackerlliste);
			cpuplayerright = ans.getIsComputerCorrect();
			cputime = ans.getComputerTime(bean.getCurrentQuestion().getCorrectChoices());
			playerright = ans.validateWith(bean.getCurrentQuestion().getCorrectChoices());
			playertime = timeleft;
		}
		
		gamebean.getPlayer1RoundSummary().add(playerright);
		gamebean.getPlayer2RoundSummary().add(cpuplayerright);
		gamebean.setPlayer1RoundTime(gamebean.getPlayer1RoundTime()+playertime);
		gamebean.setPlayer1TotalTime(gamebean.getPlayer1TotalTime()+playertime);
		gamebean.setPlayer2RoundTime(gamebean.getPlayer2RoundTime()+cputime);
		gamebean.setPlayer2TotalTime(gamebean.getPlayer2TotalTime()+cputime);
	}
	
	public void nextQuestion(Round r, GameBean bean){
		this.gamebean = bean;
		Question q = r.next();
		gamebean.setCurrentQuestion(q);
	}
	
	public void determineRoundsWinner(GameBean bean){
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
			bean.setPlayer2WonRounds(bean.getPlayer2WonRounds()+1);
			bean.setCurrentRoundWinner(bean.getPlayer2());
		} else if(player1Points > player2Points){
			bean.setPlayer1WonRounds(bean.getPlayer1WonRounds()+1);
			bean.setCurrentRoundWinner(bean.getPlayer1());
		} else{
			if(bean.getPlayer1RoundTime() < bean.getPlayer2RoundTime()){
				bean.setPlayer1WonRounds(bean.getPlayer1WonRounds()+1);
				bean.setCurrentRoundWinner(bean.getPlayer1());	
			} else if(bean.getPlayer1RoundTime() > bean.getPlayer2RoundTime()){
				bean.setPlayer2WonRounds(bean.getPlayer2WonRounds()+1);
				bean.setCurrentRoundWinner(bean.getPlayer2());
			}else{
				bean.setCurrentRoundWinner("Niemand");
				
			}
		}
	}

	public void determineWinner(GameBean bean){
	
		if(bean.getPlayer1WonRounds() > bean.getPlayer2WonRounds()){
			bean.setGameWinner(bean.getPlayer1());
		}
		else if(bean.getPlayer1WonRounds() < bean.getPlayer2WonRounds()){
			bean.setGameWinner(bean.getPlayer2());
		}
		else{
			bean.setGameWinner("Niemand");
		}
	}
}