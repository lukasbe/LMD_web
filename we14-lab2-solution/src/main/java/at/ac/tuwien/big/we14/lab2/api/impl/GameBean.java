package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameBean {
	
	private String player1 = "Hugobert";
	private List<Boolean> player1RoundSummary = new ArrayList<Boolean>();
	private int player1WonRounds = 99;
	private int player1RoundTime;
	private String player2 = "Computer";
	private List<Boolean> player2RoundSummary = new ArrayList<Boolean>();
	private int player2WonRounds = 11;
	private int player2RoundTime;
	private int currentRound;
	private String currentRoundWinner = "bean predefined roundwin";
	private int player1TotalTime;
	private int player2TotalTime;
	private String gameWinner = "bean predefined gamewin";
	private int roundsQuantity;
	private int questionsQuantity;
	private Question currentQuestion;
	protected static Logger log = Logger.getLogger(GameBean.class);
	private int roundCount;
	private Round currentRoundObj;
	private List<String> roundList = new ArrayList<String>();
	private HashMap<String, Round> Game = new HashMap<String, Round>();
	
	
	
	
	
	
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public List<Boolean> getPlayer1RoundSummary() {
		return player1RoundSummary;
	}
	public void setPlayer1RoundSummary(List<Boolean> player1RoundSummary) {
		this.player1RoundSummary = player1RoundSummary;
	}
	public int getPlayer1WonRounds() {
		return player1WonRounds;
	}
	public void setPlayer1WonRounds(int player1WonRounds) {
		this.player1WonRounds = player1WonRounds;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public List<Boolean> getPlayer2RoundSummary() {
		return player2RoundSummary;
	}
	public void setPlayer2RoundSummary(List<Boolean> player2RoundSummary) {
		this.player2RoundSummary = player2RoundSummary;
	}
	public int getPlayer2WonRounds() {
		return player2WonRounds;
	}
	public void setPlayer2WonRounds(int player2WonRounds) {
		this.player2WonRounds = player2WonRounds;
	}
	public int getCurrentRound() {
		return currentRound;
	}
	public void setCurrentRound(int currentRound) {
		log.info("current round in bean gesetzt:"+currentRound);
		this.currentRound = currentRound;
	}
	public String getCurrentRoundWinner() {
		return currentRoundWinner;
	}
	public void setCurrentRoundWinner(String currentRoundWinner) {
		this.currentRoundWinner = currentRoundWinner;
	}
	public String getGameWinner() {
		return gameWinner;
	}
	public void setGameWinner(String gameWinner) {
		this.gameWinner = gameWinner;
	}
	public int getRoundsQuantity() {
		return roundsQuantity;
	}
	public void setRoundsQuantity(int roundsQuantity) {
		this.roundsQuantity = roundsQuantity;
	}
	public int getQuestionsQuantity() {
		return questionsQuantity;
	}
	public void setQuestionsQuantity(int questionsQuantity) {
		this.questionsQuantity = questionsQuantity;
	}
	public Question getCurrentQuestion() {
		log.info("getCurrentQuestion sagt "+currentQuestion);
		return currentQuestion;
	}
	public void setCurrentQuestion(Question currentQuestion) {
		log.info("current question in bean gesetzt:"+currentQuestion);
		this.currentQuestion = currentQuestion;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public Round getCurrentRoundObj() {
		return currentRoundObj;
	}
	public void setCurrentRoundObj(Round currentRoundObj) {
		this.currentRoundObj = currentRoundObj;
	}
	public List<String> getRoundList() {
		return roundList;
	}
	public void setRoundList(List<String> roundList) {
		this.roundList = roundList;
	}
	public HashMap<String, Round> getGame() {
		return Game;
	}
	public void setGame(HashMap<String, Round> game) {
		Game = game;
	}
	public int getPlayer1RoundTime() {
		return player1RoundTime;
	}
	public void setPlayer1RoundTime(int player1RoundTime) {
		this.player1RoundTime = player1RoundTime;
	}
	public int getPlayer2RoundTime() {
		return player2RoundTime;
	}
	public void setPlayer2RoundTime(int player2RoundTime) {
		this.player2RoundTime = player2RoundTime;
	}
	public int getPlayer1TotalTime() {
		return player1TotalTime;
	}
	public void setPlayer1TotalTime(int player1TotalTime) {
		this.player1TotalTime = player1TotalTime;
	}
	public int getPlayer2TotalTime() {
		return player2TotalTime;
	}
	public void setPlayer2TotalTime(int player2TotalTime) {
		this.player2TotalTime = player2TotalTime;
	}

}
