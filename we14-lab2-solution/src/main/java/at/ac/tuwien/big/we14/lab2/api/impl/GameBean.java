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
	private int currentround;
	private String gameWinner;
	private String currentroundwinner;
	private int roundsQuantity;
	private int questionsQuantity;
	private Question currentQuestion;

}
