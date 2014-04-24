package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.List;

import at.ac.tuwien.big.we14.lab2.api.Question;

public class GameBean {
	
	private String player1;
	private List<Boolean> player1RoundSummary;
	private int player1WonRounds;
	private String player2;
	private List<Boolean> player2RoundSummary;
	private int player2WonRounds;
	private int currentRound;
	private String currentRoundWinner;
	private String gameWinner;
	private int roundsQuantity;
	private int questionsQuantity;
	private Question currentQuestion;
	
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
		return currentQuestion;
	}
	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

}
